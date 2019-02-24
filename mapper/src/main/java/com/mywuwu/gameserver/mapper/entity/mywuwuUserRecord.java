package com.mywuwu.gameserver.mapper.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mywuwu_user_record")
public class mywuwuUserRecord {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "game_type")
    private Boolean gameType;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "pay_type")
    private Boolean payType;

    @Column(name = "total_games")
    private Integer totalGames;

    private Integer score;

    @Column(name = "record_info")
    private String recordInfo;

    private String remark;

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
     * @return game_type
     */
    public Boolean getGameType() {
        return gameType;
    }

    /**
     * @param gameType
     */
    public void setGameType(Boolean gameType) {
        this.gameType = gameType;
    }

    /**
     * @return room_id
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * @return pay_type
     */
    public Boolean getPayType() {
        return payType;
    }

    /**
     * @param payType
     */
    public void setPayType(Boolean payType) {
        this.payType = payType;
    }

    /**
     * @return total_games
     */
    public Integer getTotalGames() {
        return totalGames;
    }

    /**
     * @param totalGames
     */
    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    /**
     * @return score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return record_info
     */
    public String getRecordInfo() {
        return recordInfo;
    }

    /**
     * @param recordInfo
     */
    public void setRecordInfo(String recordInfo) {
        this.recordInfo = recordInfo;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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