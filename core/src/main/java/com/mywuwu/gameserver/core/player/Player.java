package com.mywuwu.gameserver.core.player;

import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import lombok.Data;

@Data
public class Player {
    /**
     * 游戏的socket
     */
    private GameWebSocketSession gameWebSocketSession;
    /**
     * 筹码
     */
    public int chip;
    /**
     * 是否准备
     */
    private boolean isReady;
    /**
     * 房间号
     */
    private String roomId;
    /**
     * 默认关系
     */
    private boolean isDisConnection;

    /**
     * 默认原型
     */
    private boolean isDisbanded;

    private boolean isOp;

    public Player(int chip, boolean isReady, GameWebSocketSession gameWebSocketSession) {
        this.chip = chip;
        this.isReady = isReady;
        this.gameWebSocketSession = gameWebSocketSession;
    }
}
