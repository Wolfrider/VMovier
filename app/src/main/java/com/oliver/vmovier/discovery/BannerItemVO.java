package com.oliver.vmovier.discovery;

import java.util.Locale;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType.Discovery;

public class BannerItemVO implements IRVItemVO {

    private String mId;

    private String mImageUrl;

    private String mParam;

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setImageUrl(String url) {
        mImageUrl = url;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setParam(String param) {
        mParam = param;
    }

    public String getParam() {
        return mParam;
    }

    @Override
    public int getType() {
        return Discovery.BANNER;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "{BannerItemVO, id = %s, image = %s}", mId, mImageUrl);
    }
}
