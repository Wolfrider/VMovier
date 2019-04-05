package com.oliver.vmovier.core.dto;

import java.util.Locale;

import com.alibaba.fastjson.annotation.JSONField;

public class CategoryDTO {

    @JSONField(name = "cate_type")
    private int mType;

    @JSONField(name = "cateid")
    private String mId;

    @JSONField(name = "catename")
    private String mName;

    @JSONField(name = "alias")
    private String mAlias;

    @JSONField(name = "icon")
    private String mIconUrl;

    @JSONField(name = "orderid")
    private int mOrder;

    @JSONField(name = "link")
    private String mLink;

    @JSONField(name = "tab")
    private String mTab;

    @JSONField(name = "cate_type")
    public int getType() {
        return mType;
    }

    @JSONField(name = "cate_type")
    public void setType(int type) {
        mType = type;
    }

    @JSONField(name = "cateid")
    public String getId() {
        return mId;
    }

    @JSONField(name = "cateid")
    public void setId(String id) {
        mId = id;
    }

    @JSONField(name = "catename")
    public String getName() {
        return mName;
    }

    @JSONField(name = "catename")
    public void setName(String name) {
        mName = name;
    }

    @JSONField(name = "alias")
    public String getAlias() {
        return mAlias;
    }

    @JSONField(name = "alias")
    public void setAlias(String alias) {
        mAlias = alias;
    }

    @JSONField(name = "icon")
    public String getIconUrl() {
        return mIconUrl;
    }

    @JSONField(name = "icon")
    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    @JSONField(name = "orderid")
    public int getOrder() {
        return mOrder;
    }

    @JSONField(name = "orderid")
    public void setOrder(int order) {
        mOrder = order;
    }

    @JSONField(name = "link")
    public String getLink() {
        return mLink;
    }

    @JSONField(name = "link")
    public void setLink(String link) {
        mLink = link;
    }

    @JSONField(name = "tab")
    public String getTab() {
        return mTab;
    }

    @JSONField(name = "tab")
    public void setTab(String tab) {
        mTab = tab;
    }

    @Override
    public String toString() {
        return String.format(Locale.CHINA, "{CategoryDTO: id = %s, name = %s, iconUrl = %s, tab = %s}",
            mId, mName, mIconUrl, mTab);
    }
}
