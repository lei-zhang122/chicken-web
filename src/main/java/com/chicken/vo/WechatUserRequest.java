package com.chicken.vo;

/**
 * @author zhanglei
 * @date 2019-09-02 17:26
 */
public class WechatUserRequest {

    private String id;

    private String nickName;

    private String phone;

    private String avatar;

    private String openid;

    private String unionid;

    private String sex;

    private String inviteNum;

    private String status;

    private String createTime;

    private String currentPage;

    private String regSource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(String inviteNum) {
        this.inviteNum = inviteNum;
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

    public String getRegSource() {
        return regSource;
    }

    public void setRegSource(String regSource) {
        this.regSource = regSource;
    }

    @Override
    public String toString() {
        return "WechatUserRequest{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", sex='" + sex + '\'' +
                ", inviteNum='" + inviteNum + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", regSource='" + regSource + '\'' +
                '}';
    }
}
