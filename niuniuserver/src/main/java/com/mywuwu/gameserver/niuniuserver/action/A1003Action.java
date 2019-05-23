
package com.mywuwu.gameserver.niuniuserver.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.core.room.RoomContext;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.data.monoModel.UserModel;
import com.mywuwu.gameserver.data.monoRepository.UserRepository;
import com.mywuwu.gameserver.niuniuserver.player.NiuNiuPlayer;
import com.mywuwu.gameserver.niuniuserver.protocolData.request.A1003Request;
import com.mywuwu.gameserver.niuniuserver.protocolData.response.A1003Response;
import com.mywuwu.gameserver.niuniuserver.protocolData.response.ErrorResponse;
import com.mywuwu.gameserver.niuniuserver.room.NiuNiuRoomActorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

/*
 * @description: 创建房间
 * @author: lianglele
 * @date: 2019-03-08
 */
@Component
@Protocol(1003)
public class A1003Action extends BaseAction implements RoomAction<A1003Request, RoomContext> {

    @Value("${logicserver.connector}")
    private String connectorName;

    @Value("${logicserver.name}")
    private String serverName;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private NiuNiuRoomActorManager roomActorManager;
    @Autowired
    private UserRepository userRepository;


    //最小开局人数
    private int playerLowerlimit = 2;


    //最大开局人数
    private int playerUpLimit = 8;



    @Override
    public void requestAction(TransferData optionalTransferData) throws IOException {
        A1003Request a1003Request = unPackJson(optionalTransferData.getData(), A1003Request.class);
        String id = optionalTransferData.getGameWebSocketSession().getId();

        GameWebSocketSession session = optionalTransferData.getGameWebSocketSession();

        Optional<UserModel> optionalUserModel = this.userRepository.findByName(id);


        if (session.getRoomNumber() == null || session.getRoomNumber().isEmpty()) {
            optionalUserModel.ifPresent(userModel -> {
                if (userModel.getUserType() == 0) {
                    // 创建牛牛信息
                    NiuNiuPlayer p = new NiuNiuPlayer(0, true, session);
                    p.setGameWebSocketSession(session);
                    session.setChannel(serverName);
                    // 获取房间号，并创建房间
                    String roomNumber = roomActorManager.createRoomActor(p,
                            playerLowerlimit,
                            playerUpLimit,
                            redisTemplate,
                            a1003Request.getXzxz(),
                            a1003Request.getJushu());

                    session.setRoomNumber(roomNumber);
                    // 把session缓存到redis中
                    this.valueOperationsByGameWebSocketSession.set(id, session);

                    try {
                        //返回用户信息
                        send(new A1003Response(roomNumber), optionalTransferData, connectorName);
                    } catch (JsonProcessingException e) {
                        logger.error("json error:", e);
                    }
                } else {
                    // 访客登陆后不让创建房间默认发卡不够
                    try {
                        optionalTransferData.setProtocol(9999);
                        send(new ErrorResponse("房卡不足"), optionalTransferData, connectorName);
                    } catch (JsonProcessingException e) {
                        logger.error("json error:", e);
                    }
                }
            });
        } else {
            send(new A1003Response(session.getRoomNumber()), optionalTransferData, connectorName);
        }
    }

    @Override
    public void roomAction(A1003Request message, RoomContext context) {

    }
}
