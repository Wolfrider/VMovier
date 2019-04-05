package com.oliver.vmovier.detail.article;

import android.os.Parcel;
import com.oliver.vmovier.core.Constant.CardType.ArticleDetail;

public class ImageVO implements IArticleItemVO {

    private int mWidth;

    private int mHeight;

    private String mUrl;

    public ImageVO() {
    }

    private ImageVO(Parcel args) {
        mWidth = args.readInt();
        mHeight = args.readInt();
        mUrl = args.readString();
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mWidth);
        dest.writeInt(mHeight);
        dest.writeString(mUrl);
    }

    public static final Creator<ImageVO> CREATOR = new Creator<ImageVO>() {
        @Override
        public ImageVO createFromParcel(Parcel source) {
            return new ImageVO(source);
        }

        @Override
        public ImageVO[] newArray(int size) {
            return new ImageVO[0];
        }
    };

    @Override
    public int getType() {
        return ArticleDetail.IMAGE;
    }
}
