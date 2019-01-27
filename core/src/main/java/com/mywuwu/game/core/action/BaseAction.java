package com.mywuwu.game.core.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mywuwu.game.core.TransferData;
import com.mywuwu.game.core.network.websocket.GameWebSocketSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description： 请求父类
 * @Author: 梁乐乐
 * @Date: Created in 2019/1/27 9:24
 */
public abstract class BaseAction {

    @Autowired
    protected  RedisTemplate redisTemplate;
    protected  ValueOperations<String,GameWebSocketSession> valueOperationsByGameWebSocketSession;

    @PostConstruct
    protected void init(){
        this.valueOperationsByGameWebSocketSession = this.redisTemplate.opsForValue();
    }


    /**
     * 请求消息处理
     * @param optionalTransferData 请求报文
     */
    public abstract void requestAction(TransferData optionalTransferData) throws IOException;


    protected <T> T unPackJson(byte[] array, Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(array, tClass);
    }

    protected Map<String, String> unPackJson(byte[] array) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(array, new HashMap<String, String>().getClass());
    }

    private byte[] packJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(o);
    }


    @Async
    protected void send(Object o,TransferData transferData,String connector) throws JsonProcessingException {
        byte[] data = packJson(o);
        transferData.setData(data);
        this.redisTemplate.convertAndSend(connector, transferData);
    }




}
