package com.mywuwu.gameserver.core.room;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.mywuwu.gameserver.core.annotation.Protocol;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 房间消息信息
 */
@Data
@Component
public abstract class RoomManager {
    /**
     * 保存全部房间模型
     */
    protected ConcurrentHashMap<String, ActorRef> map = new ConcurrentHashMap<>();
    /**
     * 缓存房间处理
     */
    Map<Integer, RoomAction> cacheMap = new HashMap<>();


    /**
     * 链接名
     */
    protected String connectorName;
    /**
     * 服务名
     */
    protected String serverName;
    /**
     * 缓存名
     */
    protected RedisTemplate redisTemplate;
    /**
     * 模型对象
     */
    protected ActorSystem actorSystem;
    /**
     * spring反射类
     */
    protected ApplicationContext context;


    public RoomManager( RedisTemplate redisTemplate, ActorSystem actorSystem, ApplicationContext context) {
        this.redisTemplate = redisTemplate;
        this.actorSystem = actorSystem;
        this.context = context;
    }

    public ActorRef getRoomActorRef(String roomId){
        return map.get(roomId);
    }

    public void clearRoom(String roomId){
        ActorRef actorRef =  map.remove(roomId);
        actorSystem.stop(actorRef);
    }


    @PostConstruct
    public void init() {
        for (String bean : context.getBeanNamesForAnnotation(Protocol.class)) {
            Object o = context.getBean(bean);
            Protocol protocol = o.getClass().getAnnotation(Protocol.class);

            if(o.getClass().getSuperclass().equals(RoomAction.class)) {
                cacheMap.put(protocol.value(), (RoomAction) o);
            }
        }
    }
}
