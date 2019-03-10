package com.mywuwu.gameserver.niuniuserver.protocolData.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 连接
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectResponse {
    String name;
}
