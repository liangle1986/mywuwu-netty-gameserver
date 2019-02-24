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
}
