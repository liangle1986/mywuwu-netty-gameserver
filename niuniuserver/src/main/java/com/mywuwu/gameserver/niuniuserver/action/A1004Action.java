/*
 * @description: 加入房间
 * @author: lianglele
 * @date: 2019-03-08
 */

package com.mywuwu.gameserver.niuniuserver.action;

import akka.actor.ActorRef;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.niuniuserver.player.NiuNiuPlayer;
import com.mywuwu.gameserver.niuniuserver.protocolData.request.A1004Request;
import com.mywuwu.gameserver.niuniuserver.protocolData.response.A1004Response;
import com.mywuwu.gameserver.niuniuserver.protocolData.response.ErrorResponse;
import com.mywuwu.gameserver.niuniuserver.room.NiuNiuRoomActorManager;
import com.mywuwu.gameserver.niuniuserver.room.NiuNiuRoomContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Protocol(1004)
public class A1004Action extends BaseAction implements RoomAction<A1004Request, NiuNiuRoomContext> {

    @Autowired
    private NiuNiuRoomActorManager roomActorManager;


    @Override
    public void requestAction(TransferData optionalTransferData) throws IOException {

        //获取要加入房间到数据参数
        if (optionalTransferData.getData() != null) {
            try {
                A1004Request a1004Request = unPackJson(optionalTransferData.getData(), A1004Request.class);
                ActorRef actorRef = roomActorManager.getRoomActorRef(a1004Request.getRoomId());
                actorRef.tell(a1004Request, null);
            } catch (IOException ioException) {

            }
        }
    }

    @Override
    public void roomAction(A1004Request message, NiuNiuRoomContext context) {
        //获取redis中的session
        GameWebSocketSession session = this.valueOperationsByGameWebSocketSession.get(message.getName());

        //创建字节的牛牛对象
        NiuNiuPlayer player = new NiuNiuPlayer(0, true, session);

        //验证房间人数上限
        if (context.getPlayerList().size() <= context.getPlayerUpLimit()) {
            //把自己的牌加入到对象中
            context.getPlayerList().add(player);
            //房间记录下来
            session.setRoomNumber(context.getRoomNumber());
            session.setChannel(context.getServerName());

            //重新设置缓存
            this.valueOperationsByGameWebSocketSession.set(player.getGameWebSocketSession().getId(), session);

            //通知房间所有玩家
            context.sendAll(new A1004Response(context.getPlayerList().toArray(new NiuNiuPlayer[0])[0], context.getRoomNumber()), 1004);

            // 判断是否符合最小开局人数
            if (context.getPlayerList().size() >= context.getPlayerLowerlimit()) {
                context.beginGame();
            }
        } else {
            context.send(new ErrorResponse("房间已满"), new TransferData(session, context.getServerName(), 9999, null));
        }
    }
}
