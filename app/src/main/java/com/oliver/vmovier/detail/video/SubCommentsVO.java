package com.oliver.vmovier.detail.video;

import java.util.Locale;

public class SubCommentsVO {

    private String mAvatarUrl;

    private String mUserName;

    private int mTimestamp;

    private String mContent;

    private int mApproveCount;

    private String mReplayUserName;

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getTime() {
        return "";
    }

    public void setTimestamp(int timestamp) {
        mTimestamp = timestamp;
    }

    public String getContent() {
        return String.format(Locale.CHINA, "回复 %s：%s", mReplayUserName, mContent);
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getApproveCount() {
        return mApproveCount;
    }

    public void setApproveCount(int approveCount) {
        mApproveCount = approveCount;
    }

    public void setReplayUserName(String replayUserName) {
        mReplayUserName = replayUserName;
    }
}
