package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class CommentItemDTO {

    @JSONField(name = "content")
    private String mContent;

    @JSONField(name = "addtime")
    private int mPublishTime;

    @JSONField(name = "count_approve")
    private int mApproveCount;

    @JSONField(name = "userinfo")
    private UserInfoDTO mUserInfo;

    @JSONField(name = "reply_userinfo")
    private UserInfoDTO mReplyUserInfo;

    @JSONField(name = "subcomment")
    private List<CommentItemDTO> mSubComments;

    @JSONField(name = "content")
    public String getContent() {
        return mContent;
    }

    @JSONField(name = "content")
    public void setContent(String content) {
        mContent = content;
    }

    @JSONField(name = "addtime")
    public int getPublishTime() {
        return mPublishTime;
    }

    @JSONField(name = "addtime")
    public void setPublishTime(int publishTime) {
        mPublishTime = publishTime;
    }

    @JSONField(name = "count_approve")
    public int getApproveCount() {
        return mApproveCount;
    }

    @JSONField(name = "count_approve")
    public void setApproveCount(int approveCount) {
        mApproveCount = approveCount;
    }

    @JSONField(name = "userinfo")
    public UserInfoDTO getUserInfo() {
        return mUserInfo;
    }

    @JSONField(name = "userinfo")
    public void setUserInfo(UserInfoDTO userInfo) {
        mUserInfo = userInfo;
    }

    @JSONField(name = "reply_userinfo")
    public UserInfoDTO getReplyUserInfo() {
        return mReplyUserInfo;
    }

    @JSONField(name = "reply_userinfo")
    public void setReplyUserInfo(UserInfoDTO replyUserInfo) {
        mReplyUserInfo = replyUserInfo;
    }

    @JSONField(name = "subcomment")
    public List<CommentItemDTO> getSubComments() {
        return mSubComments;
    }

    @JSONField(name = "subcomment")
    public void setSubComments(List<CommentItemDTO> subComments) {
        mSubComments = subComments;
    }
}
