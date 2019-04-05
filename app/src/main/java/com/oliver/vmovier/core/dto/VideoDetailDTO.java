package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class VideoDetailDTO {

    @JSONField(name = "cate")
    private List<String> mCates;

    @JSONField(name = "intro")
    private String mIntro;

    @JSONField(name = "publish_time")
    private int mPublishTime;

    @JSONField(name = "title")
    private String mTitle;

    @JSONField(name = "format_content")
    private List<FormatContentItemDTO> mArticleContent;

    @JSONField(name = "count_like")
    private int mLikeCount;

    @JSONField(name = "count_share")
    private int mShareCount;

    @JSONField(name = "share_link")
    private String mShareLink;

    @JSONField(name = "share_title")
    private String mShareTitle;

    @JSONField(name = "duration")
    private int mDuration;

    @JSONField(name = "content_video")
    private List<VideoContentDTO> mVideoList;

    @JSONField(name = "relate_video")
    private List<RelateVideoDTO> mRelateVideo;

    @JSONField(name = "count_comment")
    private int mCommentsCount;

    @JSONField(name = "comment")
    private CommentDTO mComments;

    @JSONField(name = "editor")
    private EditorDTO mEditor;

    @JSONField(name = "cate")
    public List<String> getCates() {
        return mCates;
    }

    @JSONField(name = "cate")
    public void setCates(List<String> cates) {
        mCates = cates;
    }

    @JSONField(name = "intro")
    public String getIntro() {
        return mIntro;
    }

    @JSONField(name = "intro")
    public void setIntro(String intro) {
        mIntro = intro;
    }

    @JSONField(name = "publish_time")
    public int getPublishTime() {
        return mPublishTime;
    }

    @JSONField(name = "publish_time")
    public void setPublishTime(int publishTime) {
        mPublishTime = publishTime;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return mTitle;
    }

    @JSONField(name = "title")
    public void setTitle(String title) {
        mTitle = title;
    }

    @JSONField(name = "format_content")
    public List<FormatContentItemDTO> getArticleContent() {
        return mArticleContent;
    }

    @JSONField(name = "format_content")
    public void setArticleContent(List<FormatContentItemDTO> articleContent) {
        mArticleContent = articleContent;
    }

    @JSONField(name = "count_like")
    public int getLikeCount() {
        return mLikeCount;
    }

    @JSONField(name = "count_like")
    public void setLikeCount(int likeCount) {
        mLikeCount = likeCount;
    }

    @JSONField(name = "count_share")
    public int getShareCount() {
        return mShareCount;
    }

    @JSONField(name = "count_share")
    public void setShareCount(int shareCount) {
        mShareCount = shareCount;
    }

    @JSONField(name = "content_video")
    public List<VideoContentDTO> getVideoList() {
        return mVideoList;
    }

    @JSONField(name = "content_video")
    public void setVideoList(List<VideoContentDTO> videoList) {
        mVideoList = videoList;
    }

    @JSONField(name = "relate_video")
    public List<RelateVideoDTO> getRelateVideo() {
        return mRelateVideo;
    }

    @JSONField(name = "relate_video")
    public void setRelateVideo(List<RelateVideoDTO> relateVideo) {
        mRelateVideo = relateVideo;
    }

    @JSONField(name = "count_comment")
    public int getCommentsCount() {
        return mCommentsCount;
    }

    @JSONField(name = "count_comment")
    public void setCommentsCount(int commentsCount) {
        mCommentsCount = commentsCount;
    }

    @JSONField(name = "comment")
    public CommentDTO getComments() {
        return mComments;
    }

    @JSONField(name = "comment")
    public void setComments(CommentDTO comments) {
        mComments = comments;
    }

    @JSONField(name = "share_link")
    public String getShareLink() {
        return mShareLink;
    }

    @JSONField(name = "share_link")
    public void setShareLink(String shareLink) {
        mShareLink = shareLink;
    }

    @JSONField(name = "share_title")
    public String getShareTitle() {
        return mShareTitle;
    }

    @JSONField(name = "share_title")
    public void setShareTitle(String shareTitle) {
        mShareTitle = shareTitle;
    }

    @JSONField(name = "duration")
    public int getDuration() {
        return mDuration;
    }

    @JSONField(name = "duration")
    public void setDuration(int duration) {
        mDuration = duration;
    }

    @JSONField(name = "editor")
    public EditorDTO getEditor() {
        return mEditor;
    }

    @JSONField(name = "editor")
    public void setEditor(EditorDTO editor) {
        mEditor = editor;
    }
}
