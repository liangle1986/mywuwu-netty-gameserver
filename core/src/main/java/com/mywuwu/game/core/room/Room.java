package com.mywuwu.game.core.room;

import akka.actor.AbstractActor;
import com.mywuwu.game.core.annotation.Protocol;
import com.mywuwu.game.core.room.message.baseMessage.RoomMessage;

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
