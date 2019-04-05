package com.oliver.vmovier.detail.video;

public class InteractiveVO {

    private int mLikeCount;

    private int mShareCount;

    private String mShareLink;

    private String mShareTitle;

    public int getLikeCount() {
        return mLikeCount;
    }

    public void setLikeCount(int likeCount) {
        mLikeCount = likeCount;
    }

    public int getShareCount() {
        return mShareCount;
    }

    public void setShareCount(int shareCount) {
        mShareCount = shareCount;
    }

    public String getShareLink() {
        return mShareLink;
    }

    public void setShareLink(String shareLink) {
        mShareLink = shareLink;
    }

    public String getShareTitle() {
        return mShareTitle;
    }

    public void setShareTitle(String shareTitle) {
        mShareTitle = shareTitle;
    }
}
