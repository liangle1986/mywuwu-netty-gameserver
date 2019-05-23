package com.mywuwu.gameserver.niuniuserver.protocolData.request;


import com.mywuwu.gameserver.core.annotation.Protocol;
import com.mywuwu.gameserver.core.room.message.baseMessage.RoomMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Protocol(1003)
public class A1003Request implements RoomMessage {
//    private int playerLowerlimit;
//    private int playerUpLimit;
//    private  int xiaZhuTop;
//    private int juShu;
    private String userName;
    private int dizhu;
    private int jushu;
    private int zfType;
    private int fbgz;
    private int zjType;
    private int ts1;
    private int ts2;
    private int ts3;
    private int ts4;
    private int tuizhu;
    private int jzjr;
    private int jzcp;
    private int xzxz;
    private int zdks;
    private int qzbs;
    private String qunid;
    private String headImgurl;

}
