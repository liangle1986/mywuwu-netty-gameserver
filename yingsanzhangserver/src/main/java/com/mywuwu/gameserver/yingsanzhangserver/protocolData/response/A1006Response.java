package com.mywuwu.gameserver.yingsanzhangserver.protocolData.response;


import com.mywuwu.gameserver.yingsanzhangserver.player.YingSanZhangPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下一个玩家操作
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1006Response {
    private YingSanZhangPlayer yingSanZhangPlayer;
}
