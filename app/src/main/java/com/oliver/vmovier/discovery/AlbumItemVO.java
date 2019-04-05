package com.oliver.vmovier.discovery;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType.Discovery;

public class AlbumItemVO implements IRVItemVO {

    private String mId;

    private String mTitle;

    private String mSubTitle;

    private String mImageUrl;

    private String mRequestUrl;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        this.mSubTitle = subTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getRequestUrl() {
        return mRequestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.mRequestUrl = requestUrl;
    }

    @Override
    public int getType() {
        return Discovery.ALBUM;
    }
}
