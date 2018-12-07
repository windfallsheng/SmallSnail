package com.smallsnailtech.smallsnail.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: luzhaosheng
 * @date
 * @Version: <p>
 * 页面浏览历史记录
 */
public class PageHistoryEntity<T> implements Serializable {

    private int dataType;
    private String pageFlag;
    private String pageName;
    private T data;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag) {
        this.pageFlag = pageFlag;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageHistoryEntity{" +
                "dataType=" + dataType +
                ", pageFlag='" + pageFlag + '\'' +
                ", pageName='" + pageName + '\'' +
                ", data=" + data +
                '}';
    }
}
