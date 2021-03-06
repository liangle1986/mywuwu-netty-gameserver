/*
 * @author   作者: qugang
 * @E-mail   邮箱: qgass@163.com
 * @date     创建时间：2018/11/19
 * 类说明     准备
 */
package com.mywuwu.gameserver.yingsanzhangserver.action;

import akka.actor.ActorRef;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.BaseAction;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.room.RoomAction;
import com.mywuwu.gameserver.yingsanzhangserver.player.YingSanZhangPlayer;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.request.A1009Request;
import com.mywuwu.gameserver.yingsanzhangserver.protocolData.response.A1009Response;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomActorManager;
import com.mywuwu.gameserver.yingsanzhangserver.room.YingSanZhangRoomContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Protocol(1009)
public class A1009Action extends BaseAction implements RoomAction<A1009Request, YingSanZhangRoomContext> {

    @Autowired
    private YingSanZhangRoomActorManager roomActorManager;

    @Override
    public void requestAction(TransferData optionalTransferData) throws IOException {
        A1009Request a1009Request = unPackJson(optionalTransferData.getData(), A1009Request.class);


        ActorRef actorRef = roomActorManager.getRoomActorRef(a1009Request.getRoomId());

        actorRef.tell(a1009Request, null);
    }

    @Override
    public void roomAction(A1009Request message, YingSanZhangRoomContext context) {

        Optional<Player> optionalPlayer = context.getPlayer(message.getName());

        if(optionalPlayer.isPresent())
        {
            YingSanZhangPlayer player = (YingSanZhangPlayer) optionalPlayer.get();
            player.setReady(true);
            context.sendAll(new A1009Response(player), 1009);
            if (context.getPlayerList().stream().anyMatch(p -> ((Player) p).isReady())) {
                context.beginGame();
            }
        }
    }
}
