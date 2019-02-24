package com.mywuwu.gameserver.mapper.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mywuwu_user")
public class MywuwuUser {
    @Id
    private String id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "head_img_url")
    private String headImgUrl;

    @Column(name = "wx_open_id")
    private String wxOpenId;

    @Column(name = "room_card_num")
    private Integer roomCardNum;

    @Column(name = "user_level")
    private Boolean userLevel;

    @Column(name = "win_probability")
    private Integer winProbability;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return head_img_url
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * @param headImgUrl
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /**
     * @return wx_open_id
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     * @param wxOpenId
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    /**
     * @return room_card_num
     */
    public Integer getRoomCardNum() {
        return roomCardNum;
    }

    /**
     * @param roomCardNum
     */
    public void setRoomCardNum(Integer roomCardNum) {
        this.roomCardNum = roomCardNum;
    }

    /**
     * @return user_level
     */
    public Boolean getUserLevel() {
        return userLevel;
    }

    /**
     * @param userLevel
     */
    public void setUserLevel(Boolean userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * @return win_probability
     */
    public Integer getWinProbability() {
        return winProbability;
    }

    /**
     * @param winProbability
     */
    public void setWinProbability(Integer winProbability) {
        this.winProbability = winProbability;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}