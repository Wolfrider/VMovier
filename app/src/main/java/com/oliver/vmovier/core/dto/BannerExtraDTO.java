package com.oliver.vmovier.core.dto;

import java.util.Locale;

import com.alibaba.fastjson.annotation.JSONField;

public class BannerExtraDTO {

    @JSONField(name = "app_banner_type")
    private int mType;

    @JSONField(name = "app_banner_param")
    private String mParam;

    @JSONField(name = "app_banner_type")
    public int getType() {
        return mType;
    }

    @JSONField(name = "app_banner_type")
    public void setType(int type) {
        mType = type;
    }

    @JSONField(name = "app_banner_param")
    public String getParam() {
        return mParam;
    }

    @JSONField(name = "app_banner_param")
    public void setParam(String param) {
        mParam = param;
    }

    @Override
    public String toString() {
        return String.format(Locale.CHINA, "{BannerExtraDTO: type = %d,  param = %s}", mType, mParam);
    }
}
