package com.mywuwu.gameserver.card.poker.YingSanZhang;


import com.mywuwu.game.card.poker.PokerManager;

public class YingSanZhangPoker extends PokerManager {
    public YingSanZhang getPocker(){
        return new YingSanZhang(this.get(),this.get(),this.get());
    }

}
