package com.oliver.vmovier.core.post;

import java.util.Locale;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType;

public class PostVO implements IRVItemVO {

    private String mId;

    private String mTitle;

    private String mSubTitle;

    private String mImageUrl;

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setSubTitle(String cateName, int duration) {
        mSubTitle = String.format(Locale.CHINA, "%s  /  %d'%02d\"", cateName, duration/60, duration%60);
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public int getType() {
        return CardType.POST;
    }
}
