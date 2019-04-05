package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class CommentDTO {

    @JSONField(name = "list")
    private List<CommentItemDTO> mCommentList;

    @JSONField(name = "next_page_url")
    private String mNextPageUrl;

    @JSONField(name = "list")
    public List<CommentItemDTO> getCommentList() {
        return mCommentList;
    }

    @JSONField(name = "list")
    public void setCommentList(List<CommentItemDTO> commentList) {
        mCommentList = commentList;
    }

    @JSONField(name = "next_page_url_full")
    public String getNextPageUrl() {
        return mNextPageUrl;
    }

    @JSONField(name = "next_page_url_full")
    public void setNextPageUrl(String nextPageUrl) {
        mNextPageUrl = nextPageUrl;
    }
}
