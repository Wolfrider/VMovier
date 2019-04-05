package com.oliver.vmovier.core.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class VideoBucketDTO {

    @JSONField(name = "width")
    private int mWidth;

    @JSONField(name = "height")
    private int mHeight;

    @JSONField(name = "display_aspect_ratio")
    private String mDisplayRatio;

    @JSONField(name = "duration")
    private int mDuration;

    @JSONField(name = "https_url")
    private String mUrl;

    @JSONField(name = "profile")
    private String mProfile;

    @JSONField(name = "width")
    public int getWidth() {
        return mWidth;
    }

    @JSONField(name = "width")
    public void setWidth(int width) {
        mWidth = width;
    }

    @JSONField(name = "height")
    public int getHeight() {
        return mHeight;
    }

    @JSONField(name = "height")
    public void setHeight(int height) {
        mHeight = height;
    }

    @JSONField(name = "display_aspect_ratio")
    public String getDisplayRatio() {
        return mDisplayRatio;
    }

    @JSONField(name = "display_aspect_ratio")
    public void setDisplayRatio(String displayRatio) {
        mDisplayRatio = displayRatio;
    }

    @JSONField(name = "duration")
    public int getDuration() {
        return mDuration;
    }

    @JSONField(name = "duration")
    public void setDuration(int duration) {
        mDuration = duration;
    }

    @JSONField(name = "https_url")
    public String getUrl() {
        return mUrl;
    }

    @JSONField(name = "https_url")
    public void setUrl(String url) {
        mUrl = url;
    }

    @JSONField(name = "profile")
    public String getProfile() {
        return mProfile;
    }

    @JSONField(name = "profile")
    public void setProfile(String profile) {
        mProfile = profile;
    }
}
