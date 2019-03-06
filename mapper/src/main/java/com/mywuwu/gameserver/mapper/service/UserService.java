package com.mywuwu.gameserver.mapper.service;

import com.mywuwu.gameserver.mapper.entity.MywuwuUser;


/**
 * @Auther: 梁乐乐
 * @Date: 2019/2/15 09:59
 * @Description:
 */
public interface UserService {
    /*
     * @description:
     * @param: [wxOpenId]
     * @return: com.mywuwu.gameserver.mapper.entity.MywuwuUser
     * @author: lianglele
     * @date: 2019-02-24
     */
    MywuwuUser selectWeixinCode(String wxOpenId);

    /*
     * @description: 创建用户
     * @param: [user] 用户信息
     * @return: void
     * @author: lianglele
     * @date: 2019-03-06
     */
    void saveGameUser(MywuwuUser user);
}
