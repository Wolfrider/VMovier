package com.oliver.vmovier.detail.video;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType.VideoDetail;
import com.oliver.vmovier.core.utils.TimeUtils;

public class CommentsVO implements IRVItemVO {

    private int mTotalCount;

    private String mAvatarUrl;

    private String mUserName;

    private int mPublishTime;

    private String mContent;

    private int mApproveCount;

    private List<CommentsVO> mSubCommentList;

    public CommentsVO() {
    }

    public String getTotalCount() {
        if (mTotalCount > 0) {
            return String.format(Locale.CHINA, "%d 条评论", mTotalCount);
        } else {
            return "";
        }
    }

    public void setTotalCount(int totalCount) {
        mTotalCount = totalCount;
    }

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

    public String getPublishTime() {
        long diff = TimeUtils.getDiffHour(mPublishTime);
        if (diff <= 23) {
            return String.format(Locale.CHINA, "%d小时前", diff);
        } else {
            diff = TimeUtils.getDiffDay(mPublishTime);
            return String.format(Locale.CHINA, "%d天前", diff);
        }
    }

    public void setPublishTime(int timestamp) {
        mPublishTime = timestamp;
    }

    public String getContent() {
        return mContent;
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

    public List<CommentsVO> getSubCommentList() {
        return mSubCommentList;
    }

    public void setSubCommentList(List<CommentsVO> subCommentList) {
        mSubCommentList = subCommentList;
    }

    @Override
    public int getType() {
        return VideoDetail.COMMENT;
    }
}
