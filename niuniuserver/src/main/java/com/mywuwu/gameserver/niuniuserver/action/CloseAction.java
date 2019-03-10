package com.mywuwu.gameserver.niuniuserver.action;

import akka.actor.ActorRef;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.niuniuserver.protocolData.request.A1002Request;
import com.mywuwu.gameserver.niuniuserver.protocolData.response.CloseResponse;
import com.mywuwu.gameserver.niuniuserver.room.NiuNiuRoomActorManager;
import com.mywuwu.gameserver.niuniuserver.room.NiuNiuRoomContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 关闭链接
 */
@Component
@Protocol(1001)
public class CloseAction extends BaseAction implements RoomAction<A1002Request, NiuNiuRoomContext> {


    @Autowired
    private NiuNiuRoomActorManager roomActorManager;


    @Override
    public void requestAction(TransferData optionalTransferData) {
        //获取当前用户信息
        GameWebSocketSession gameWebSocketSession = this.valueOperationsByGameWebSocketSession.get(optionalTransferData.getGameWebSocketSession().getId());


        /**
         * 如果有房间号通知房间所以玩家
         */
        if (gameWebSocketSession.getRoomNumber() != null) {
            ActorRef actorRef = roomActorManager.getRoomActorRef(gameWebSocketSession.getRoomNumber());

            actorRef.tell(new A1002Request(gameWebSocketSession.getId(), gameWebSocketSession), null);
        }
    }

    @Override
    public void roomAction(A1002Request message, NiuNiuRoomContext context) {
        //通知房间所以玩家
        Optional<Player> p = context.getPlayer(message.getName());
        p.ifPresent(player -> {
            player.setDisConnection(true);
            player.setGameWebSocketSession(message.getSession());
            context.sendAll(new CloseResponse(player.getGameWebSocketSession().getId()), 1001);
        });
    }
}
