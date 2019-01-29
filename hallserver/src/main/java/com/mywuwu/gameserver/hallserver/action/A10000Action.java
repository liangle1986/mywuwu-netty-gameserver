/*
 * @author   作者: qugang
 * @E-mail   邮箱: qgass@163.com
 * @date     创建时间：2018/11/19
 * 类说明     创建房间
 */
package com.mywuwu.gameserver.hallserver.action;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.core.room.RoomContext;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.hallserver.GameServer;
import com.mywuwu.gameserver.hallserver.protocolData.request.A10000Request;
import com.mywuwu.gameserver.hallserver.protocolData.response.A10000Response;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Protocol(10000)
public class A10000Action extends BaseAction implements RoomAction<A10000Request, RoomContext> {


//    @Autowired
//    private UserRepository userRepository;

    @Override
    public void requestAction(TransferData optionalTransferData) throws IOException {
        A10000Request a1003Request = unPackJson(optionalTransferData.getData(), A10000Request.class);
        String id = optionalTransferData.getGameWebSocketSession().getId();

        GameWebSocketSession session = optionalTransferData.getGameWebSocketSession();

//        Optional<UserModel> optionalUserModel = this.userRepository.findById(Long.valueOf(id));

        GameServer.send(session.getSessionId(), JSON.toJSONString(new A10000Response("asfasdfdasdfdasdfasfdasdfas")));
    }

    @Override
    public void roomAction(A10000Request message, RoomContext context) {

    }
}
