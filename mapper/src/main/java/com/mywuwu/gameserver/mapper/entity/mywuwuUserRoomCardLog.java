package com.mywuwu.gameserver.mapper.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mywuwu_user_room_card_log")
public class mywuwuUserRoomCardLog {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "pre_room_card_num")
    private Integer preRoomCardNum;

    @Column(name = "cur_room_card_num")
    private Integer curRoomCardNum;

    @Column(name = "diff_room_card_num")
    private Integer diffRoomCardNum;

    private Integer amount;

    @Column(name = "game_type")
    private Boolean gameType;

    @Column(name = "operator_id")
    private String operatorId;

    @Column(name = "operator_type")
    private Boolean operatorType;

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
     * @return pre_room_card_num
     */
    public Integer getPreRoomCardNum() {
        return preRoomCardNum;
    }

    /**
     * @param preRoomCardNum
     */
    public void setPreRoomCardNum(Integer preRoomCardNum) {
        this.preRoomCardNum = preRoomCardNum;
    }

    /**
     * @return cur_room_card_num
     */
    public Integer getCurRoomCardNum() {
        return curRoomCardNum;
    }

    /**
     * @param curRoomCardNum
     */
    public void setCurRoomCardNum(Integer curRoomCardNum) {
        this.curRoomCardNum = curRoomCardNum;
    }

    /**
     * @return diff_room_card_num
     */
    public Integer getDiffRoomCardNum() {
        return diffRoomCardNum;
    }

    /**
     * @param diffRoomCardNum
     */
    public void setDiffRoomCardNum(Integer diffRoomCardNum) {
        this.diffRoomCardNum = diffRoomCardNum;
    }

    /**
     * @return amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
     * @return operator_id
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return operator_type
     */
    public Boolean getOperatorType() {
        return operatorType;
    }

    /**
     * @param operatorType
     */
    public void setOperatorType(Boolean operatorType) {
        this.operatorType = operatorType;
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