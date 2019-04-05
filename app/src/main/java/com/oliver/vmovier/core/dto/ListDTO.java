package com.oliver.vmovier.core.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ListDTO {

    @JSONField(name = "banner")
    private ListContentDTO<BannerDTO> mBanner;

    @JSONField(name = "album")
    private ListContentDTO<ListItemDTO> mAlum;

    @JSONField(name = "hot")
    private ListContentDTO<ListItemDTO> mHot;

    @JSONField(name = "posts")
    private ListContentDTO<ListItemDTO> mPost;

    @JSONField(name = "today")
    private ListContentDTO<ListItemDTO> mToday;

    @JSONField(name = "banner")
    public ListContentDTO<BannerDTO> getBanner() {
        return mBanner;
    }

    @JSONField(name = "banner")
    public void setBanner(ListContentDTO<BannerDTO> banner) {
        mBanner = banner;
    }

    @JSONField(name = "today")
    public ListContentDTO<ListItemDTO> getToday() {
        return mToday;
    }

    @JSONField(name = "today")
    public void setToday(ListContentDTO<ListItemDTO> today) {
        mToday = today;
    }

    @JSONField(name = "hot")
    public ListContentDTO<ListItemDTO> getHot() {
        return mHot;
    }

    @JSONField(name = "hot")
    public void setHot(ListContentDTO<ListItemDTO> hot) {
        mHot = hot;
    }

    @JSONField(name = "album")
    public ListContentDTO<ListItemDTO> getAlum() {
        return mAlum;
    }

    @JSONField(name = "album")
    public void setAlum(ListContentDTO<ListItemDTO> alum) {
        mAlum = alum;
    }

    @JSONField(name = "posts")
    public ListContentDTO<ListItemDTO> getPost() {
        return mPost;
    }

    @JSONField(name = "posts")
    public void setPost(ListContentDTO<ListItemDTO> post) {
        mPost = post;
    }
}
