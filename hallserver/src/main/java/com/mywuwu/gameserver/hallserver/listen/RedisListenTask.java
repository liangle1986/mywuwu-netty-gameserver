package com.mywuwu.gameserver.hallserver.listen;

import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.hallserver.GameServer;
import com.mywuwu.gameserver.hallserver.config.HallConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RedisListenTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HallConfig hallConfig;

    @Async
    public void doMessageTask(Message message, byte[] bytes) {
        TransferData transferData = (TransferData) this.redisTemplate.getDefaultSerializer().deserialize(message.getBody());
        if (transferData.getData() != null) {
            logger.info(new String(transferData.getData()));

            System.out.println(Integer.valueOf(hallConfig.getReverseRoutes().get(transferData.getChannel())));
            GameServer.send(Integer.valueOf(hallConfig.getReverseRoutes().get(transferData.getChannel())),
                    Integer.valueOf(transferData.getProtocol()),
                    transferData.getGameWebSocketSession().getSessionId(),
                    transferData.getData());
        }
    }
}
