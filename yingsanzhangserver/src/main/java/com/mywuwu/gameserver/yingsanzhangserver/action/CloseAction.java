package com.mywuwu.gameserver.yingsanzhangserver.action;

import akka.actor.ActorRef;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.request.A1002Request;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.response.CloseResponse;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomActorManager;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Protocol(1001)
public class CloseAction extends BaseAction implements RoomAction<A1002Request, YingSanZhangRoomContext> {


    @Autowired
    private YingSanZhangRoomActorManager roomActorManager;


    @Override
    public void requestAction(TransferData optionalTransferData) {
        GameWebSocketSession gameWebSocketSession = this.valueOperationsByGameWebSocketSession.get(optionalTransferData.getGameWebSocketSession().getId());


        if(gameWebSocketSession.getRoomNumber() != null)
        {
            ActorRef actorRef = roomActorManager.getRoomActorRef(gameWebSocketSession.getRoomNumber());

            actorRef.tell(new A1002Request(gameWebSocketSession.getId(), gameWebSocketSession), null);
        }
    }

    @Override
    public void roomAction(A1002Request message, YingSanZhangRoomContext context) {
        Optional<Player> p = context.getPlayer(message.getName());
        p.ifPresent(player -> {
            player.setDisConnection(true);
            player.setGameWebSocketSession(message.getSession());
            context.sendAll(new CloseResponse(player.getGameWebSocketSession().getId()), 1001);
        });
    }
}
