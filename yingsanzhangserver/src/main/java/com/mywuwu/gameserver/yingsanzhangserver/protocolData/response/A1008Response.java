package com.mywuwu.gameserver.yingsanzhangserver.protocolData.response;

import com.mywuwu.gameserver.yingsanzhangserver.player.YingSanZhangPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 比牌
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1008Response {
    YingSanZhangPlayer player;
    YingSanZhangPlayer toPlayer;
}
