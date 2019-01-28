package com.mywuwu.gameserver.core.player;

import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import lombok.Data;

@Data
public class Player {
    private GameWebSocketSession gameWebSocketSession;
    public int chip;
    private boolean isReady;
    private String roomId;
    private boolean isDisConnection;
    private boolean isDisbanded;

    private boolean isOp;

    public Player(int chip, boolean isReady, GameWebSocketSession gameWebSocketSession) {
        this.chip = chip;
        this.isReady = isReady;
        this.gameWebSocketSession = gameWebSocketSession;
    }

    public GameWebSocketSession getGameWebSocketSession() {
        return gameWebSocketSession;
    }

    public void setGameWebSocketSession(GameWebSocketSession gameWebSocketSession) {
        this.gameWebSocketSession = gameWebSocketSession;
    }

    public int getChip() {
        return chip;
    }

    public void setChip(int chip) {
        this.chip = chip;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public boolean isDisConnection() {
        return isDisConnection;
    }

    public void setDisConnection(boolean disConnection) {
        isDisConnection = disConnection;
    }

    public boolean isDisbanded() {
        return isDisbanded;
    }

    public void setDisbanded(boolean disbanded) {
        isDisbanded = disbanded;
    }

    public boolean isOp() {
        return isOp;
    }

    public void setOp(boolean op) {
        isOp = op;
    }
}
