package com.oliver.vmovier.core.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class TextAttrDTO implements Serializable {

    @JSONField(name = "align")
    private String mAlign;

    @JSONField(name = "color")
    private String mColor;

    @JSONField(name = "size")
    private String mSize;

    @JSONField(name = "width")
    private int mWidth;

    @JSONField(name = "height")
    private int mHeight;

    @JSONField(name = "src")
    private String mUrl;

    @JSONField(name = "align")
    public String getAlign() {
        return mAlign;
    }

    @JSONField(name = "align")
    public void setAlign(String align) {
        mAlign = align;
    }

    @JSONField(name = "color")
    public String getColor() {
        return mColor;
    }

    @JSONField(name = "color")
    public void setColor(String color) {
        mColor = color;
    }

    @JSONField(name = "size")
    public String getSize() {
        return mSize;
    }

    @JSONField(name = "size")
    public void setSize(String size) {
        mSize = size;
    }

    @JSONField(name = "width")
    public int getWidth() {
        return mWidth;
    }

    @JSONField(name = "width")
    public void setWidth(int width) {
        mWidth = width;
    }

    @JSONField(name = "height")
    public int getHeight() {
        return mHeight;
    }

    @JSONField(name = "height")
    public void setHeight(int height) {
        mHeight = height;
    }

    @JSONField(name = "src")
    public String getUrl() {
        return mUrl;
    }

    @JSONField(name = "src")
    public void setUrl(String url) {
        mUrl = url;
    }
}
