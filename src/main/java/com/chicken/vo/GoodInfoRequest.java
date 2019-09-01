package com.chicken.vo;

public class GoodInfoRequest {

    private String id;

    private String goodType;

    private String goodName;

    private String goodImg;

    private String goodNum;

    private String goodDetail;

    private String goodPrice;

    private String goodVirtual;

    private String goodDownVirtual;

    private String goodStatus;

    private String status;

    private String createTime;

    private String modifyTime;

    private String createUser;

    private String currentPage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public String getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(String goodDetail) {
        this.goodDetail = goodDetail;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodVirtual() {
        return goodVirtual;
    }

    public void setGoodVirtual(String goodVirtual) {
        this.goodVirtual = goodVirtual;
    }

    public String getGoodDownVirtual() {
        return goodDownVirtual;
    }

    public void setGoodDownVirtual(String goodDownVirtual) {
        this.goodDownVirtual = goodDownVirtual;
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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public String getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(String goodStatus) {
        this.goodStatus = goodStatus;
    }

    @Override
    public String toString() {
        return "GoodInfoRequest{" +
                "id='" + id + '\'' +
                ", goodType='" + goodType + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodImg='" + goodImg + '\'' +
                ", goodNum='" + goodNum + '\'' +
                ", goodDetail='" + goodDetail + '\'' +
                ", goodPrice='" + goodPrice + '\'' +
                ", goodVirtual='" + goodVirtual + '\'' +
                ", goodDownVirtual='" + goodDownVirtual + '\'' +
                ", goodStatus='" + goodStatus + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", currentPage='" + currentPage + '\'' +
                '}';
    }
}
