package com.mywuwu.gameserver.mapper.entity;

import javax.persistence.*;

public class User {
    @Id
    private String id;

    private String username;

    private String password;

    @Column(name = "nickName")
    private String nickname;

    @Column(name = "mobileNumber")
    private Double mobilenumber;

    private String sponsor;

    @Column(name = "cardNumber")
    private Integer cardnumber;

    @Column(name = "userType")
    private Integer usertype;

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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return nickName
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return mobileNumber
     */
    public Double getMobilenumber() {
        return mobilenumber;
    }

    /**
     * @param mobilenumber
     */
    public void setMobilenumber(Double mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    /**
     * @return sponsor
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * @param sponsor
     */
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * @return cardNumber
     */
    public Integer getCardnumber() {
        return cardnumber;
    }

    /**
     * @param cardnumber
     */
    public void setCardnumber(Integer cardnumber) {
        this.cardnumber = cardnumber;
    }

    /**
     * @return userType
     */
    public Integer getUsertype() {
        return usertype;
    }

    /**
     * @param usertype
     */
    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}