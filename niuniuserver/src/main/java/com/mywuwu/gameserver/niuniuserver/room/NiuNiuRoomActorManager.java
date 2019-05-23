package com.mywuwu.gameserver.niuniuserver.room;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import com.mywuwu.gameserver.core.redisTool.RedisTool;
import com.mywuwu.gameserver.core.room.Room;
import com.mywuwu.gameserver.core.room.RoomManager;
import com.mywuwu.gameserver.niuniuserver.player.NiuNiuPlayer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class NiuNiuRoomActorManager extends RoomManager {

    @Autowired
    private ApplicationContext context;


    @Value("${logicserver.connector}")
    private String logicserverConnector;

    @Value("${logicserver.name}")
    private String logicserverName;

    @Autowired
    public NiuNiuRoomActorManager(
            RedisTemplate redisTemplate,
            ActorSystem actorSystem,
            ApplicationContext context) {
        super(redisTemplate, actorSystem, context);

        this.connectorName = this.logicserverConnector;
        this.serverName = this.logicserverName;
    }


    public String createRoomActor(NiuNiuPlayer player,
                                  int playerLowerlimit,
                                  int playerUpLimit,
                                  RedisTemplate redisTemplate,
                                  int xiaZhuTop,
                                  int juShu) {
        String roomNumber = RedisTool.getRoomId(this.redisTemplate, "room", -1);
//                RedisTool.inc(this.redisTemplate, "room", -1);

        NiuNiuRoomContext niuNiuRoomContext = (NiuNiuRoomContext) context.getBean(NiuNiuRoomContext.class
                , roomNumber,
                playerUpLimit,
                playerLowerlimit,
                redisTemplate,
                player,
                serverName,
                connectorName,
                this);

        ActorRef actorRef = actorSystem.actorOf(new RoundRobinPool(1).props(Props.create(Room.class, niuNiuRoomContext)));
        map.put(roomNumber, actorRef);
        return roomNumber;
    }

    public ActorRef getRoomActorRef(String roomId) {
        return map.getOrDefault(roomId, null);
    }

    public void clearRoom(String roomId) {
        ActorRef actorRef = map.remove(roomId);
        actorSystem.stop(actorRef);
    }
}