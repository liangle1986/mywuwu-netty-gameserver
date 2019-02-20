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

/**
 * 房间信息
 */
@Data
@Component
@Scope(value = "prototype")
@NoArgsConstructor
public abstract class RoomContext {
    /**
     * 房间号
     */
    protected String roomNumber;
    /**
     * 开始/结束（最大人数）
     */
    protected int playerUpLimit;
    /**
     * 下局开始/结束（最小人数）
     */
    protected int playerLowerlimit;
    /**
     * 缓存
     */
    protected RedisTemplate redisTemplate;
    /**
     * 播放信息
     */
    protected Player master;
    /**
     * 是否存在 true统一取消false不同意
     */
    protected boolean isDisbanded;
    /**
     * 回合
     */
    protected int inningsNumber;
    /**
     * 当前局
     */
    protected int currentInningsNUmber;
    /**
     * 服务名
     */
    protected String serverName;
    /**
     * 链接名
     */
    protected String connectorName;

    /**
     * 局集合
     */
    protected volatile CircularList<? super Player> playerList;
    /**
     * 房间信息
     */
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
}
