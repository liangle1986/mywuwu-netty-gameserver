package com.mywuwu.gameserver.niuniuserver.protocolData.response;

import com.mywuwu.gameserver.niuniuserver.player.NiuNiuPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 加入房间
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1004Response {
    private NiuNiuPlayer niuNiuPlayers;
    private String roomNumber;
}