package com.mywuwu.gameserver.yingsanzhangserver.protocolData.request;

import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.message.baseMessage.RoomMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Protocol(1012)
public class A1012Request implements RoomMessage {
    private String name;
    private String roomId;
}
