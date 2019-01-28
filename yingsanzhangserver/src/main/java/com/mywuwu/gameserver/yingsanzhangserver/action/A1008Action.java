package com.mywuwu.gameserver.yingsanzhangserver.action;


import akka.actor.ActorRef;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.yingsanzhangserver.player.YingSanZhangPlayer;
import com.mywuwu.gameserver.yingsanzhangserver.player.YingSanZhangPlayerState;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.request.A1008Request;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.response.A1008Response;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomActorManager;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Protocol(1008)
public class A1008Action extends BaseAction implements RoomAction<A1008Request, YingSanZhangRoomContext> {

    @Autowired
    private YingSanZhangRoomActorManager roomActorManager;



    @Override
    public void requestAction(TransferData optionalTransferData) throws IOException {

        A1008Request a1008Request = unPackJson(optionalTransferData.getData(), A1008Request.class);

        ActorRef actorRef = roomActorManager.getRoomActorRef(a1008Request.getRoomId());

        actorRef.tell(a1008Request,null);
    }

    @Override
    public void roomAction(A1008Request message, YingSanZhangRoomContext context) {
        YingSanZhangPlayer yingSanZhangPlayer = (YingSanZhangPlayer) context.getPlayerList().element();
        Optional<Player> optionalPlayer = context.getPlayer(message.getToName());
        optionalPlayer.ifPresent(p -> {
            YingSanZhangPlayer currentPlayer = (YingSanZhangPlayer) p;
            int result = yingSanZhangPlayer.compareTo(currentPlayer);
            if (result > 0) {
                yingSanZhangPlayer.setState(YingSanZhangPlayerState.shu);
                context.sendAll(new A1008Response(yingSanZhangPlayer, currentPlayer), 1008);
            } else if (result < 0) {
                yingSanZhangPlayer.setState(YingSanZhangPlayerState.shu);
                context.sendAll(new A1008Response(currentPlayer, yingSanZhangPlayer), 1008);
            } else {
            }
            context.getPlayerList().poll();
            context.next();
        });
    }
}
