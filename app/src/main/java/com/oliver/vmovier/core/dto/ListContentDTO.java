package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class ListContentDTO<TArrayData> {

    @JSONField(name = "selection_title")
    private String mTitle;

    @JSONField(name = "total")
    private int mSize;

    @JSONField(name = "scheme")
    private String mScheme;

    @JSONField(name = "resource_type")
    private int mResType;

    @JSONField(name = "next_page_url")
    private String mNextPageUrl;

    @JSONField(name = "next_page_url_full")
    private String mNextPageFullUrl;

    @JSONField(name = "list")
    private List<TArrayData> mArrayData;

    @JSONField(name = "selection_title")
    public String getTitle() {
        return mTitle;
    }

    @JSONField(name = "selection_title")
    public void setTitle(String title) {
        mTitle = title;
    }

    @JSONField(name = "total")
    public int getSize() {
        return mSize;
    }

    @JSONField(name = "total")
    public void setSize(int size) {
        mSize = size;
    }

    @JSONField(name = "scheme")
    public String getScheme() {
        return mScheme;
    }

    @JSONField(name = "scheme")
    public void setScheme(String scheme) {
        mScheme = scheme;
    }

    @JSONField(name = "resource_type")
    public int getResType() {
        return mResType;
    }

    @JSONField(name = "resource_type")
    public void setResType(int resType) {
        mResType = resType;
    }

    @JSONField(name = "next_page_url")
    public String getNextPageUrl() {
        return mNextPageUrl;
    }

    @JSONField(name = "next_page_url")
    public void setNextPageUrl(String url) {
        mNextPageUrl = url;
    }

    @JSONField(name = "next_page_url_full")
    public String getNextPageFullUrl() {
        return mNextPageFullUrl;
    }

    @JSONField(name = "next_page_url_full")
    public void setNextPageFullUrl(String nextPageFullUrl) {
        mNextPageFullUrl = nextPageFullUrl;
    }

    @JSONField(name = "list")
    public List<TArrayData> getArrayData() {
        return mArrayData;
    }

    @JSONField(name = "list")
    public void setArrayData(List<TArrayData> data) {
        mArrayData = data;
    }
}
