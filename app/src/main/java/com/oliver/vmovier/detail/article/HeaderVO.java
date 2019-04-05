package com.oliver.vmovier.detail.article;

import android.os.Parcel;
import android.os.Parcelable;
import com.oliver.vmovier.core.Constant.CardType.ArticleDetail;

public class HeaderVO implements IArticleItemVO {

    private String mTitle;

    public HeaderVO() {
    }

    public HeaderVO(Parcel args) {
        mTitle = args.readString();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
    }

    @Override
    public int getType() {
        return ArticleDetail.HEADER;
    }

    public static final Parcelable.Creator<HeaderVO> CREATOR = new Creator<HeaderVO>() {

        @Override
        public HeaderVO createFromParcel(Parcel source) {
            return new HeaderVO(source);
        }

        @Override
        public HeaderVO[] newArray(int size) {
            return new HeaderVO[0];
        }
    };

}
