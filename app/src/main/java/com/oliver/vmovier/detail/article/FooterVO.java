package com.oliver.vmovier.detail.article;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;
import com.oliver.vmovier.core.Constant.CardType.ArticleDetail;

public class FooterVO implements IArticleItemVO {

    private String mAuthor;

    private String mPublishTime;

    public FooterVO() {
    }

    private FooterVO(Parcel args) {
        mAuthor = args.readString();
        mPublishTime = args.readString();
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(int publishTime) {
        mPublishTime = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date(publishTime*1000L));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAuthor);
        dest.writeString(mPublishTime);
    }

    @Override
    public int getType() {
        return ArticleDetail.FOOTER;
    }

    public static final Parcelable.Creator<FooterVO> CREATOR = new Parcelable.Creator<FooterVO>() {
        @Override
        public FooterVO createFromParcel(Parcel source) {
            return new FooterVO(source);
        }

        @Override
        public FooterVO[] newArray(int size) {
            return new FooterVO[0];
        }
    };
}
