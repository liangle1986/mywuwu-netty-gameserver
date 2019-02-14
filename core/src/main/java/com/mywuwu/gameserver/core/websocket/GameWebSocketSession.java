package com.mywuwu.gameserver.core.websocket;


import io.netty.channel.ChannelId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 游戏socket链接信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameWebSocketSession implements Serializable {
    @Id
    private String id;
    /**
     * session对象标识
     */
    private ChannelId sessionId;
    private String token;
    /**
     * 登录时间
     */
    private String lastLoginTime;
    /**
     * 退出时间
     */
    private String lastLogoutTime;
    //0: 登陆 1:退出
    private String state;
    /**
     *地址
     */
    private String address;
    /**
     * 申请类型
     */
    private String channel;
    /**
     * 房间号
     */
    private String roomNumber;

}
