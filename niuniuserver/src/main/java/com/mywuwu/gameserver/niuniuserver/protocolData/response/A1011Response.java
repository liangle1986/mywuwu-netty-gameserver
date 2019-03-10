package com.mywuwu.gameserver.niuniuserver.protocolData.response;


import com.mywuwu.gameserver.niuniuserver.player.NiuNiuPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 重新连接返回参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1011Response {
    private double zhuoMain;
    NiuNiuPlayer[] players;
}
