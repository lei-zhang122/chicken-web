package com.chicken.vo;

/**
 * @author zhanglei
 * @date 2019-09-03 19:23
 */
public class DictionaryRequest {

    private String id;

    private String dictType;

    private String dictName;

    private String dictContent;

    private String dictOrder;

    private String dictDetail;

    private String status;

    private String createUser;

    private String createTime;

    private String editTime;

    private String differentFlag;

    private String currentPage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictContent() {
        return dictContent;
    }

    public void setDictContent(String dictContent) {
        this.dictContent = dictContent;
    }

    public String getDictOrder() {
        return dictOrder;
    }

    public void setDictOrder(String dictOrder) {
        this.dictOrder = dictOrder;
    }

    public String getDictDetail() {
        return dictDetail;
    }

    public void setDictDetail(String dictDetail) {
        this.dictDetail = dictDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
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

    public String getDifferentFlag() {
        return differentFlag;
    }

    public void setDifferentFlag(String differentFlag) {
        this.differentFlag = differentFlag;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "DictionaryRequest{" +
                "id='" + id + '\'' +
                ", dictType='" + dictType + '\'' +
                ", dictName='" + dictName + '\'' +
                ", dictContent='" + dictContent + '\'' +
                ", dictOrder='" + dictOrder + '\'' +
                ", dictDetail='" + dictDetail + '\'' +
                ", status='" + status + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editTime='" + editTime + '\'' +
                ", differentFlag='" + differentFlag + '\'' +
                ", currentPage='" + currentPage + '\'' +
                '}';
    }
}
