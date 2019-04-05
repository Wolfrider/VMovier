package com.oliver.vmovier.core.dto;

import java.util.Locale;

import com.alibaba.fastjson.annotation.JSONField;

public class FirstScreenDTO {

    @JSONField(name = "id")
    private String mId;

    @JSONField(name = "click_type")
    private int mClickType;

    @JSONField(name = "content")
    private String mContent;

    @JSONField(name = "image")
    private String mImageUrl;

    @JSONField(name = "duration")
    private int mDuration;

    @JSONField(name = "id")
    public String getId() {
        return mId;
    }

    @JSONField(name = "id")
    public void setId(String id) {
        mId = id;
    }

    @JSONField(name = "click_type")
    public int getClickType() {
        return mClickType;
    }

    @JSONField(name = "click_type")
    public void setClickType(int clickType) {
        mClickType = clickType;
    }

    @JSONField(name = "content")
    public String getContent() {
        return mContent;
    }

    @JSONField(name = "content")
    public void setContent(String content) {
        mContent = content;
    }

    @JSONField(name = "image")
    public String getImageUrl() {
        return mImageUrl;
    }

    @JSONField(name = "image")
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @JSONField(name = "duration")
    public int getDuration() {
        return mDuration;
    }

    @JSONField(name = "duration")
    public void setDuration(int duration) {
        mDuration = duration;
    }

    @Override
    public String toString() {
        return String.format(Locale.CHINA, "{FirstScreenDTO: id = %s, imageUrl = %s}", mId, mImageUrl);
    }
}
