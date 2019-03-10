package com.mywuwu.gameserver.niuniuserver.player;

import com.mywuwu.gameserver.card.poker.NiuNiu.NiuNiu;
import com.mywuwu.gameserver.core.player.Player;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NiuNiuPlayer extends Player implements Comparable<NiuNiuPlayer> {

    private NiuNiu niuNiu;
    private NiuNiuPlayerState state;

    public NiuNiuPlayer(int score,
                        Boolean isReady,
                        GameWebSocketSession session) {
        super(score, isReady, session);
        this.setState(NiuNiuPlayerState.none);
        this.setReady(true);
    }


    @Override
    public int compareTo(NiuNiuPlayer o) {
        return this.getNiuNiu().compareTo(o.getNiuNiu());
    }
}
