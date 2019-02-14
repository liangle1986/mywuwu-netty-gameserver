package com.mywuwu.gameserver.core.room;

import akka.actor.AbstractActor;
import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.message.baseMessage.RoomMessage;

/**
 * 通过akka创建房间模型
 */
public class Room extends AbstractActor {

    private RoomContext roomContext;

    Room(RoomContext roomContext) {
        this.roomContext = roomContext;
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RoomMessage.class, message -> {
            Integer protocol = message.getClass().getAnnotation(Protocol.class).value();
            roomContext.getRoomManager().getCacheMap().get(protocol).roomAction(message, roomContext);
        }).build();
    }
}
