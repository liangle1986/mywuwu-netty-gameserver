package com.mywuwu.gameserver.mapper.mapper;

import com.mywuwu.gameserver.mapper.common.mapper.MyMapper;
import com.mywuwu.gameserver.mapper.entity.MywuwuUser;

public interface MywuwuUserMapper extends MyMapper<MywuwuUser> {

    /*
     * @description: 根据微信公开号查询微信用户是否存在
     * @param:  wxOpenId 微信公开号
     * @return: MywuwuUser 用户信息
     * @author: lianglele
     * @date: 2019-02-24
     */
    MywuwuUser selectWeixinCode(String wxOpenId);

    /*
     * @description: 创建
     * @param: [user]
     * @return: void
     * @author: lianglele
     * @date: 2019-03-06
     */
    void saveGameUser(MywuwuUser user);
}