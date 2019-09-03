package com.chicken.vo;

/**
 * @author zhanglei
 * @date 2019-09-02 17:26
 */
public class UserInviteRequest {

    private String id;

    private String userId;

    private String nickName;

    private String inviteUserId;

    private String inviteStatus;

    private String inviteTime;

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

    public String getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(String inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public String getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(String inviteTime) {
        this.inviteTime = inviteTime;
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
        return "UserInviteRequest{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", inviteUserId='" + inviteUserId + '\'' +
                ", inviteStatus='" + inviteStatus + '\'' +
                ", inviteTime='" + inviteTime + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", currentPage='" + currentPage + '\'' +
                '}';
    }
}
