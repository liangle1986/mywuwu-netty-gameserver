package com.mywuwu.gameserver.card.poker.NiuNiu;

import com.mywuwu.gameserver.card.poker.PokerManager;

/**
 * @program mywuwu-netty-gameserver
 * @description: 牛牛初始化牌
 * @author: lianglele
 * @create: 2019/03/07 20:27
 */
public class NiuNiuPoker extends PokerManager {

    public NiuNiu getPoker() {
        return new NiuNiu(this.get(), this.get(), this.get(), this.get(), this.get());
    }
}
