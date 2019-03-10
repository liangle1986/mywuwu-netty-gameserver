package com.mywuwu.gameserver.niuniuserver.protocolData.request;

import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.message.baseMessage.RoomMessage;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 链接房间参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Protocol(1001)
public class A1001Request implements RoomMessage {
    private GameWebSocketSession session;
    private String roomNumber;
}
