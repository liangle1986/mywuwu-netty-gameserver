package com.mywuwu.gameserver.mapper.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mywuwu_order")
public class MywuwuOrder {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "room_card_num")
    private Integer roomCardNum;

    private Integer price;

    @Column(name = "wx_pay_price")
    private Integer wxPayPrice;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "pay_status")
    private Byte payStatus;

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
     * @return product_id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
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
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return wx_pay_price
     */
    public Integer getWxPayPrice() {
        return wxPayPrice;
    }

    /**
     * @param wxPayPrice
     */
    public void setWxPayPrice(Integer wxPayPrice) {
        this.wxPayPrice = wxPayPrice;
    }

    /**
     * @return transaction_id
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return pay_status
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * @param payStatus
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
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