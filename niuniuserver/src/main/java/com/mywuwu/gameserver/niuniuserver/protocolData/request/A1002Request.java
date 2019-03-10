package com.mywuwu.gameserver.niuniuserver.protocolData.request;

import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.message.baseMessage.RoomMessage;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退出参数对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Protocol(1002)
public class A1002Request implements RoomMessage {
    private String name;
    GameWebSocketSession session;
}
