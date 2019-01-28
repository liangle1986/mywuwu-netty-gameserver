package com.mywuwu.gameserver.core.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.list.CircularList;
import com.mywuwu.gameserver.core.player.Player;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
@Component
@Scope(value = "prototype")
@NoArgsConstructor
public abstract class RoomContext {
    protected String roomNumber;
    protected int playerUpLimit;
    protected int playerLowerlimit;
    protected RedisTemplate redisTemplate;
    protected Player master;
    protected boolean isDisbanded;
    protected int inningsNumber;
    protected int currentInningsNUmber;
    protected String serverName;
    protected String connectorName;
    protected volatile CircularList<? super Player> playerList;
    protected RoomManager roomManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public RoomContext(String roomNumber,
                       int playerUpLimit,
                       int playerLowerlimit,
                       RedisTemplate redisTemplate,
                       Player master,
                       String serverName,
                       String connectorName,
                       RoomManager roomManager) {
        this.roomNumber = roomNumber;
        this.playerUpLimit = playerUpLimit;
        this.playerLowerlimit = playerLowerlimit;
        this.redisTemplate = redisTemplate;
        this.master = master;
        this.serverName = serverName;
        this.connectorName = connectorName;
        this.playerList = new CircularList<>();
        this.roomManager = roomManager;
    }

    public void sendAll(Object o, int protocol) {
        for (Object playerObject : this.playerList) {
            Player player = (Player) playerObject;
            send(o, new TransferData(player.getGameWebSocketSession(),
                    this.serverName, protocol, null));
        }
    }

    private byte[] packJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(o);
    }


    @Async
    public void send(Object o, TransferData transferData) {
        try {
            byte[] data = packJson(o);
            transferData.setData(data);
            this.redisTemplate.convertAndSend(this.connectorName, transferData);
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException ", e);
        }
    }


    public Optional<Player> getPlayer(String id) {
        for (Object player : this.playerList) {
            if (((Player) player).getGameWebSocketSession().getId().equals(id))
                return Optional.of(((Player) player));
        }
        return Optional.empty();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPlayerUpLimit() {
        return playerUpLimit;
    }

    public void setPlayerUpLimit(int playerUpLimit) {
        this.playerUpLimit = playerUpLimit;
    }

    public int getPlayerLowerlimit() {
        return playerLowerlimit;
    }

    public void setPlayerLowerlimit(int playerLowerlimit) {
        this.playerLowerlimit = playerLowerlimit;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Player getMaster() {
        return master;
    }

    public void setMaster(Player master) {
        this.master = master;
    }

    public boolean isDisbanded() {
        return isDisbanded;
    }

    public void setDisbanded(boolean disbanded) {
        isDisbanded = disbanded;
    }

    public int getInningsNumber() {
        return inningsNumber;
    }

    public void setInningsNumber(int inningsNumber) {
        this.inningsNumber = inningsNumber;
    }

    public int getCurrentInningsNUmber() {
        return currentInningsNUmber;
    }

    public void setCurrentInningsNUmber(int currentInningsNUmber) {
        this.currentInningsNUmber = currentInningsNUmber;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public CircularList<? super Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(CircularList<? super Player> playerList) {
        this.playerList = playerList;
    }

    public RoomManager getRoomManager() {
        return roomManager;
    }

    public void setRoomManager(RoomManager roomManager) {
        this.roomManager = roomManager;
    }
}
