package com.oliver.vmovier.core.dto;

import java.util.Locale;

import com.alibaba.fastjson.annotation.JSONField;

public class DailyCoverDTO {

    @JSONField(name = "content")
    private String mContent;

    @JSONField(name = "image")
    private String mImageUrl;

    @JSONField(name = "image_blurred")
    private String mBlurredImageUrl;

    @JSONField(name = "title")
    private String mTitle;

    @JSONField(name = "time")
    private TimeDTO mTime;

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

    @JSONField(name = "image_blurred")
    public String getBlurredImageUrl() {
        return mBlurredImageUrl;
    }

    @JSONField(name = "image_blurred")
    public void setBlurredImageUrl(String blurredImageUrl) {
        mBlurredImageUrl = blurredImageUrl;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return mTitle;
    }

    @JSONField(name = "title")
    public void setTitle(String title) {
        mTitle = title;
    }

    @JSONField(name = "time")
    public TimeDTO getTime() {
        return mTime;
    }

    @JSONField(name = "time")
    public void setTime(TimeDTO time) {
        mTime = time;
    }

    @Override
    public String toString() {
        return String.format(Locale.CHINA, "{DailyCoverDTO: title = %s, content = %s, imageUrl = %s, time = %s}",
            mTitle, mContent, mImageUrl, mTime);
    }
}
