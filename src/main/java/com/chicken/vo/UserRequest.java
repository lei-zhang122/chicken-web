package com.chicken.vo;

/**
 * @description: 用户请求参数
 * @author: zhanglei
 * @create: 2018-07-30 14:07
 **/
public class UserRequest {

    private String id;

    private String userName;

    private String loginName;

    private String userPwd;

    private String status;

    private String phone;

    private String remark;

    private Integer createUser;

    private String createTime;

    private String editTime;

    private String currentPage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                ", createUser=" + createUser +
                ", createTime='" + createTime + '\'' +
                ", editTime='" + editTime + '\'' +
                ", currentPage='" + currentPage + '\'' +
                '}';
    }
}
