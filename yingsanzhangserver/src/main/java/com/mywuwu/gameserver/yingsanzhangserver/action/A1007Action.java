package com.mywuwu.gameserver.yingsanzhangserver.action;

import akka.actor.ActorRef;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.yingsanzhangserver.player.YingSanZhangPlayer;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.request.A1007Request;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.response.A1007Response;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomActorManager;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 下注
 */
@Component
@Protocol(1007)
public class A1007Action extends BaseAction implements RoomAction<A1007Request, YingSanZhangRoomContext> {


    @Autowired
    private YingSanZhangRoomActorManager roomActorManager;


    @Override
    public void requestAction(TransferData optionalTransferData) {

        if(optionalTransferData.getData() != null)
        {
            try {
                A1007Request a1007Request = unPackJson(optionalTransferData.getData(), A1007Request.class);
                ActorRef actorRef = roomActorManager.getRoomActorRef(a1007Request.getRoomId());

                actorRef.tell(a1007Request, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void roomAction(A1007Request message, YingSanZhangRoomContext context) {

        Player currentPlayer = (Player) context.getPlayerList().element();
        if (currentPlayer.chip >= message.getChip()) {
            context.deskChip += message.getChip();
            currentPlayer.chip -= message.getChip();
            context.sendAll(new A1007Response((YingSanZhangPlayer) currentPlayer, message.getType(), message.getChip()), 1007);
            context.next();
        }
        context.getPlayerList().poll();
    }
}
