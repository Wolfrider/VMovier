package com.oliver.vmovier.home.open;

import java.util.Locale;

public class FirstScreenVO {

    private int mClickType;

    private String mContent;

    private String mImagePath;

    private int mDuration;

    public FirstScreenVO() {
    }

    public int getClickType() {
        return mClickType;
    }

    public void setClickType(int clickType) {
        mClickType = clickType;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String path) {
        mImagePath = path;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "{FirstScreenVO: clickType = %d, contentId = %s, imagePath = %s, duration = %d}",
            mClickType, mContent, mImagePath, mDuration);
    }
}
