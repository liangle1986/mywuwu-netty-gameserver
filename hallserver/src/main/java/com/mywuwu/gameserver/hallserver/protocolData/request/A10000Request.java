package com.mywuwu.gameserver.hallserver.protocolData.request;


import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.message.baseMessage.RoomMessage;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Protocol(10000)
public class A10000Request implements RoomMessage {
    private int type;
    GameWebSocketSession session;
}
