package com.chicken.vo;

/**
 * @author zhanglei
 * @date 2019-09-02 17:26
 */
public class AccountUserRequest {

    private String id;

    private String userId;

    private String nickName;

    private String attentCount;

    private String consumeCount;

    private String balance;

    private String status;

    private String createTime;

    private String currentPage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAttentCount() {
        return attentCount;
    }

    public void setAttentCount(String attentCount) {
        this.attentCount = attentCount;
    }

    public String getConsumeCount() {
        return consumeCount;
    }

    public void setConsumeCount(String consumeCount) {
        this.consumeCount = consumeCount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "AccountUserRequest{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", attentCount='" + attentCount + '\'' +
                ", consumeCount='" + consumeCount + '\'' +
                ", balance='" + balance + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", currentPage='" + currentPage + '\'' +
                '}';
    }
}
