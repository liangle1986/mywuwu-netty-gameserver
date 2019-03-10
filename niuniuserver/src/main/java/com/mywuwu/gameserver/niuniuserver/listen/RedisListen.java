package com.mywuwu.gameserver.niuniuserver.listen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RedisListen extends MessageListenerAdapter {




    @Autowired
    private RedisListenTask redisListenTask;


    @Override
    public void onMessage(Message message, byte[] bytes) {
        redisListenTask.doMessageTask(message,bytes);
    }


}
