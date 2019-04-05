package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class VideoContentDTO {

    @JSONField(name = "duration")
    private int mDuration;

    @JSONField(name = "progressive_default_play_index")
    private int mDefaultIndex;

    @JSONField(name = "title")
    private String mTitle;

    @JSONField(name = "progressive")
    private List<VideoBucketDTO> mBucket;

    @JSONField(name = "duration")
    public int getDuration() {
        return mDuration;
    }

    @JSONField(name = "duration")
    public void setDuration(int duration) {
        mDuration = duration;
    }

    @JSONField(name = "progressive_default_play_index")
    public int getDefaultIndex() {
        return mDefaultIndex;
    }

    @JSONField(name = "progressive_default_play_index")
    public void setDefaultIndex(int defaultIndex) {
        mDefaultIndex = defaultIndex;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return mTitle;
    }

    @JSONField(name = "title")
    public void setTitle(String title) {
        mTitle = title;
    }

    @JSONField(name = "progressive")
    public List<VideoBucketDTO> getBucket() {
        return mBucket;
    }

    @JSONField(name = "progressive")
    public void setBucket(List<VideoBucketDTO> bucket) {
        mBucket = bucket;
    }
}
