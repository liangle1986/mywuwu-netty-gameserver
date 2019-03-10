package com.mywuwu.gameserver.niuniuserver.protocolData.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 错误
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}
