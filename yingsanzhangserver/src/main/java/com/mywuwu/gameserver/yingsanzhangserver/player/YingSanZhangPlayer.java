package com.mywuwu.gameserver.yingsanzhangserver.player;

import com.mywuwu.gameserver.card.poker.YingSanZhang.YingSanZhang;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class YingSanZhangPlayer extends Player implements Comparable<YingSanZhangPlayer> {

    private YingSanZhang yingSanZhang;
    private YingSanZhangPlayerState state;

    public YingSanZhangPlayer(int score,
                              Boolean isReady,
                              GameWebSocketSession session) {
        super(score, isReady, session);
        this.setState(YingSanZhangPlayerState.none);
        this.setReady(true);
    }


    @Override
    public int compareTo(YingSanZhangPlayer o) {
        return this.getYingSanZhang().compareTo(o.getYingSanZhang());
    }
}
