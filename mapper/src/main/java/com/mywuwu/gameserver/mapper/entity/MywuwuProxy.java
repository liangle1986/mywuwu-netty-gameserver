package com.mywuwu.gameserver.mapper.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mywuwu_proxy")
public class MywuwuProxy {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    private String password;

    @Column(name = "wechat_num")
    private String wechatNum;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "id_card_no")
    private String idCardNo;

    @Column(name = "extract_amount")
    private Long extractAmount;

    @Column(name = "remainder_amount")
    private Long remainderAmount;

    @Column(name = "total_income")
    private Long totalIncome;

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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return wechat_num
     */
    public String getWechatNum() {
        return wechatNum;
    }

    /**
     * @param wechatNum
     */
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return id_card_no
     */
    public String getIdCardNo() {
        return idCardNo;
    }

    /**
     * @param idCardNo
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    /**
     * @return extract_amount
     */
    public Long getExtractAmount() {
        return extractAmount;
    }

    /**
     * @param extractAmount
     */
    public void setExtractAmount(Long extractAmount) {
        this.extractAmount = extractAmount;
    }

    /**
     * @return remainder_amount
     */
    public Long getRemainderAmount() {
        return remainderAmount;
    }

    /**
     * @param remainderAmount
     */
    public void setRemainderAmount(Long remainderAmount) {
        this.remainderAmount = remainderAmount;
    }

    /**
     * @return total_income
     */
    public Long getTotalIncome() {
        return totalIncome;
    }

    /**
     * @param totalIncome
     */
    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
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