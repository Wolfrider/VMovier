package com.oliver.vmovier.core.dto;

import java.util.Locale;

import com.alibaba.fastjson.annotation.JSONField;

public class BannerDTO {

    @JSONField(name = "bannerid")
    private String mId;

    @JSONField(name = "image")
    private String mImageUrl;

    @JSONField(name = "extra_data")
    private BannerExtraDTO mExtra;

    @JSONField(name = "bannerid")
    public String getId() {
        return mId;
    }

    @JSONField(name = "bannerid")
    public void setId(String id) {
        mId = id;
    }

    @JSONField(name = "image")
    public String getImageUrl() {
        return mImageUrl;
    }

    @JSONField(name = "image")
    public void setImageUrl(String url) {
        mImageUrl = url;
    }

    @JSONField(name = "extra_data")
    public BannerExtraDTO getExtra() {
        return mExtra;
    }

    @JSONField(name = "extra_data")
    public void setExtra(BannerExtraDTO extra) {
        mExtra = extra;
    }

    @Override
    public String toString() {
        return String.format(Locale.CHINA, "{BannerDTO: id = %s, imageUrl = %s, extra = %s}",
            mId, mImageUrl, mExtra);
    }
}
