package com.chicken.vo;

/**
 * @author zhanglei
 * @date 2019-09-02 17:26
 */
public class GoodOrderRequest {

    private String id;

    private String goodId;

    private String userId;

    private String exchangeTime;

    private String exchangeStatus;

    private String orderNum;

    private String expressName;

    private String status;

    private String createTime;

    private String currentPage;

    private String modifyUser;

    private String modifyTime;

    private String nickName;

    private String expressNum;

    private String addressId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "GoodOrderRequest{" +
                "id='" + id + '\'' +
                ", goodId='" + goodId + '\'' +
                ", userId='" + userId + '\'' +
                ", exchangeTime='" + exchangeTime + '\'' +
                ", exchangeStatus='" + exchangeStatus + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", expressName='" + expressName + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", nickName='" + nickName + '\'' +
                ", expressNum='" + expressNum + '\'' +
                ", addressId='" + addressId + '\'' +
                '}';
    }
}
