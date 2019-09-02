package com.chicken.vo;

/**
 * @author zhanglei
 * @date 2019-09-02 17:26
 */
public class UserAddressRequest {

    private String id;

    private String userId;

    private String userAddress;

    private String contact;

    private String phone;

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

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserAddressRequest{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", currentPage='" + currentPage + '\'' +
                '}';
    }
}
