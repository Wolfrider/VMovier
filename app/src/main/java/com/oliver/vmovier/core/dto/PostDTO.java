package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class PostDTO {

    @JSONField(name = "duration")
    private int mDuration;

    @JSONField(name = "image")
    private String mImageUrl;

    @JSONField(name = "request_url")
    private String mRequestUrl;

    @JSONField(name = "title")
    private String mTitle;

    @JSONField(name = "postid")
    private String mPostId;

    @JSONField(name = "rating")
    private float mRating;

    @JSONField(name = "like_num")
    private int mLikeNum;

    @JSONField(name = "share_num")
    private int mShareNum;

    @JSONField(name = "cates")
    private List<CategoryDTO> mCates;

    @JSONField(name = "duration")
    public void setDuration(int duration) {
        mDuration = duration;
    }

    @JSONField(name = "duration")
    public int getDuration() {
        return mDuration;
    }

    @JSONField(name = "image")
    public void setImageUrl(String url) {
        mImageUrl = url;
    }

    @JSONField(name = "image")
    public String getImageUrl() {
        return mImageUrl;
    }

    @JSONField(name = "request_url")
    public void setRequestUrl(String url) {
        mRequestUrl = url;
    }

    @JSONField(name = "request_url")
    public String getRequestUrl() {
        return mRequestUrl;
    }

    @JSONField(name = "title")
    public void setTitle(String title) {
        mTitle = title;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return mTitle;
    }

    @JSONField(name = "postid")
    public void setPostId(String postId) {
        mPostId = postId;
    }

    @JSONField(name = "postid")
    public String getPostId() {
        return mPostId;
    }

    @JSONField(name = "rating")
    public void setRating(float rating) {
        mRating = rating;
    }

    @JSONField(name = "rating")
    public float getRating() {
        return mRating;
    }

    @JSONField(name = "like_num")
    public void setLikeNum(int num) {
        mLikeNum = num;
    }

    @JSONField(name = "like_num")
    public int getLinkNum() {
        return mLikeNum;
    }

    @JSONField(name = "share_num")
    public void setShareNum(int num) {
        mShareNum = num;
    }

    @JSONField(name = "share_num")
    public int getShareNum() {
        return mShareNum;
    }

    @JSONField(name = "cates")
    public void setCategroy(List<CategoryDTO> cates) {
        mCates = cates;
    }

    @JSONField(name = "cates")
    public List<CategoryDTO> getCategory() {
        return mCates;
    }

}
