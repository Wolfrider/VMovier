package com.oliver.vmovier.detail.video;

import java.util.ArrayList;
import java.util.List;

import android.util.ArrayMap;

public class ProfileVO {

    private class Item {
        private String mTitle;

        private String mUrl;

        public Item(String title, String url) {
            mTitle = title;
            mUrl = url;
        }
    }

    private List<Item> mVideoProfileList;


    private int mDefaultIndex;

    private String mTitle;

    private int mDuration;

    public ProfileVO() {
        mVideoProfileList = new ArrayList<>(16);
    }

    public void addProfile(String title, String url) {
        mVideoProfileList.add(new Item(title, url));
    }

    public String getDefaultUrl() {
        return mVideoProfileList.get(mDefaultIndex).mUrl;
    }

    public void setDefaultIndex(int defaultIndex) {
        mDefaultIndex = defaultIndex;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }
}
