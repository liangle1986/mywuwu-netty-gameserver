/*
* @author   作者: qugang
* @E-mail   邮箱: qgass@163.com
* @date     创建时间：2018/11/19
* 类说明     准备请求
*/
package com.mywuwu.gameserver.yingsanzhangserver.protocolData.request;


import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.message.baseMessage.RoomMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Protocol(1009)
public class A1009Request implements RoomMessage {
    String name;
    String roomId;
}
