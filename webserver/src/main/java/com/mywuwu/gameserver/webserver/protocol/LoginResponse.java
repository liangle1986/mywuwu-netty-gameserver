package com.mywuwu.gameserver.webserver.protocol;

import com.mywuwu.gameserver.webserver.config.GameServerList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private long userId;
    private String name;
    private String nickName;
    private String mobileNumber;
    private int sex;
    private int cardNumber;
    //0 用户 1 访客
    private int userType;
    private String headImgUrl;

    private List<GameServerList> gameServerLists;

    private String token;
}
