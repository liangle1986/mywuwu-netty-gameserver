package com.mywuwu.gameserver.yingsanzhangserver.protocolData.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 解散房间
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1012Response {
    private String name;
    private String roomId;
}
