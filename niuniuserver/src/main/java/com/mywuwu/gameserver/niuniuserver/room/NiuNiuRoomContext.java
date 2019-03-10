package com.mywuwu.gameserver.niuniuserver.room;


import com.mywuwu.gameserver.card.poker.NiuNiu.NiuNiuPoker;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.room.RoomContext;
import com.mywuwu.gameserver.niuniuserver.player.NiuNiuPlayer;
import com.mywuwu.gameserver.niuniuserver.player.NiuNiuPlayerState;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;



@Component
@Scope(value = "prototype")
@NoArgsConstructor
public class NiuNiuRoomContext extends RoomContext {

    /**
     * 筹码
     */
    public double deskChip;
    private NiuNiuPoker poker = new NiuNiuPoker();


    NiuNiuRoomContext(
            String roomNumber,
            int playerUpLimit,
            int playerLowerlimit,
            RedisTemplate redisTemplate,
            Player master,
            String serverName,
            String connectorName,
            NiuNiuRoomActorManager yingSanZhangRoomActor) {
        super(roomNumber, playerUpLimit, playerLowerlimit, redisTemplate, master, serverName, connectorName, yingSanZhangRoomActor);
        this.deskChip = 0;

    }


    public void beginGame() {
        for (Object player : this.playerList) {
            ((NiuNiuPlayer) player).setNiuNiu(poker.getPoker());
        }
        NiuNiuPlayer currentPlayer = ((NiuNiuPlayer) this.playerList.element());
        currentPlayer.setOp(true);

//        sendAll(new A1005Response(this.playerList.toArray(players), currentPlayer), 1005);
        this.currentInningsNUmber++;
    }

    public void clearRoom() {
        roomManager.clearRoom(this.roomNumber);
    }

    public void next() {
        long count = this.playerList.stream().filter(p -> ((NiuNiuPlayer) p).getState() == NiuNiuPlayerState.none).count();

        // 通知一个房间所以玩家
        if (count == 1) {
            NiuNiuPlayer niuNiuPlayer = (NiuNiuPlayer) this.playerList.stream().filter(p -> ((NiuNiuPlayer) p).getState() == NiuNiuPlayerState.win).findFirst().get();
            niuNiuPlayer.chip += this.deskChip;
            sendAll(niuNiuPlayer, 1009);
        }

        // 通知继续在玩的玩家
        for (int i = 0; i < this.getPlayerList().size(); i++) {
            NiuNiuPlayer player = (NiuNiuPlayer) this.playerList.poll();
            if (player.getState() != NiuNiuPlayerState.shu &&
                    player.getState() != NiuNiuPlayerState.qiquan) {
//                sendAll(new A1006Response(player), 1006);
                break;
            }
        }
    }
}

