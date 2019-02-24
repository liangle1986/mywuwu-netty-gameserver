package com.mywuwu.gameserver.mapper.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mywuwu_user_feedback")
public class mywuwuUserFeedback {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "feed_back")
    private String feedBack;

    private Byte type;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return mobile_phone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return feed_back
     */
    public String getFeedBack() {
        return feedBack;
    }

    /**
     * @param feedBack
     */
    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
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
}