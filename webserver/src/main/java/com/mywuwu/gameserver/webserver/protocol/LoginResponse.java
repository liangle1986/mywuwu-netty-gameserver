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

    private String nickName;

    private String headImgUrl;

    private String wxOpenId;

    private Integer roomCardNum;

    private Boolean userLevel;

    private Integer winProbability;

    private List<GameServerList> gameServerLists;

    private String token;
}
