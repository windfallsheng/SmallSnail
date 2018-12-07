package com.smallsnailtech.smallsnail.entity;

public class InfoCategoryEntity {

    private int categoryId;
    private String categoryName;
    private String categoryIconUrl;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }

    @Override
    public String toString() {
        return "InfoCategoryEntity{" +
                "categoryId=" + categoryId +
                ", categoryName=" + categoryName +
                ", categoryIconUrl=" + categoryIconUrl +
                '}';
    }
}
