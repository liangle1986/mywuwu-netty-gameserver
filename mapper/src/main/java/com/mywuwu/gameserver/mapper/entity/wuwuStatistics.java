package com.mywuwu.gameserver.mapper.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wuwu_statistics")
public class wuwuStatistics {
    @Id
    private Integer id;

    @Column(name = "access_ip")
    private String accessIp;

    @Column(name = "access_time")
    private Date accessTime;

    @Column(name = "access_number")
    private Integer accessNumber;

    @Column(name = "access_type")
    private Integer accessType;

    @Column(name = "login_user")
    private String loginUser;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return access_ip
     */
    public String getAccessIp() {
        return accessIp;
    }

    /**
     * @param accessIp
     */
    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    /**
     * @return access_time
     */
    public Date getAccessTime() {
        return accessTime;
    }

    /**
     * @param accessTime
     */
    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    /**
     * @return access_number
     */
    public Integer getAccessNumber() {
        return accessNumber;
    }

    /**
     * @param accessNumber
     */
    public void setAccessNumber(Integer accessNumber) {
        this.accessNumber = accessNumber;
    }

    /**
     * @return access_type
     */
    public Integer getAccessType() {
        return accessType;
    }

    /**
     * @param accessType
     */
    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    /**
     * @return login_user
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
}