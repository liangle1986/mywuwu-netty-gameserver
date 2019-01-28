package com.mywuwu.gameserver.yingsanzhangserver.action;

import akka.actor.ActorRef;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.yingsanzhangserver.player.YingSanZhangPlayer;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.request.A1001Request;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.response.A1011Response;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomActorManager;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Protocol(1002)
public class ConnectAction extends BaseAction implements RoomAction<A1001Request, YingSanZhangRoomContext> {

    @Autowired
    private YingSanZhangRoomActorManager roomActorManager;


    @Override
    public void requestAction(TransferData optionalTransferData) {


        GameWebSocketSession session = optionalTransferData.getGameWebSocketSession();

        ActorRef actorRef = roomActorManager.getRoomActorRef(session.getRoomNumber());
        actorRef.tell(new A1001Request(session, session.getRoomNumber()), null);
    }

    @Override
    public void roomAction(A1001Request message, YingSanZhangRoomContext context) {

        Optional<Player> optionalPlayer = context.getPlayer(message.getSession().getId());
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setGameWebSocketSession(player.getGameWebSocketSession());
            player.setDisConnection(false);
            context.send(new A1011Response(context.deskChip, context.getPlayerList().toArray(new YingSanZhangPlayer[0])),
                    new TransferData(message.getSession(),
                            context.getServerName(), 1011, null));
        }
    }
}
