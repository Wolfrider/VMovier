package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class ListItemDTO {

    @JSONField(name = "postid")
    private String mId;

    @JSONField(name = "title")
    private String mTitle;

    @JSONField(name = "app_fu_title")
    private String mSubTitle;

    @JSONField(name = "duration")
    private int mDuration;

    @JSONField(name = "image")
    private String mImageUrl;

    @JSONField(name = "request_url")
    private String mRequestUrl;

    @JSONField(name = "cates")
    private List<CategoryDTO> mCategoryList;

    @JSONField(name = "postid")
    public String getId() {
        return mId;
    }

    @JSONField(name = "postid")
    public void setId(String id) {
        mId = id;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return mTitle;
    }

    @JSONField(name = "title")
    public void setTitle(String title) {
        mTitle = title;
    }

    @JSONField(name = "app_fu_title")
    public String getSubTitle() {
        return mSubTitle;
    }

    @JSONField(name = "app_fu_title")
    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    @JSONField(name = "duration")
    public int getDuration() {
        return mDuration;
    }

    @JSONField(name = "duration")
    public void setDuration(int duration) {
        mDuration = duration;
    }

    @JSONField(name = "image")
    public String getImageUrl() {
        return mImageUrl;
    }

    @JSONField(name = "image")
    public void setImageUrl(String url) {
        mImageUrl = url;
    }

    @JSONField(name = "request_url")
    public String getRequestUrl() {
        return mRequestUrl;
    }

    @JSONField(name = "request_url")
    public void setRequestUrl(String requestUrl) {
        this.mRequestUrl = requestUrl;
    }

    @JSONField(name = "cates")
    public List<CategoryDTO> getCategoryList() {
        return mCategoryList;
    }

    @JSONField(name = "cates")
    public void setCategoryList(List<CategoryDTO> categoryList) {
        mCategoryList = categoryList;
    }

}
