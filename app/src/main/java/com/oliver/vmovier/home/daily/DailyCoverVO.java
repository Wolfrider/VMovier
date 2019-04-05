package com.oliver.vmovier.home.daily;

import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;
import com.oliver.vmovier.core.utils.Logger;

public class DailyCoverVO implements Parcelable {

    private static final String TAG = "DailyCoverVO";

    private String mContent;

    private String mImageUrl;

    private String mBackgroundImageUrl;

    private String mMonth;

    private String mDay;

    private String mWeek;

    private String mTitle;

    public DailyCoverVO() {

    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setBackgroundImageUrl(String imageUrl) {
        mBackgroundImageUrl = imageUrl;
    }

    public String getBackgroundImageUrl() {
        return mBackgroundImageUrl;
    }

    public void setMonth(String month) {
        mMonth = month;
    }

    public String getMonth() {
        return mMonth;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public String getDay() {
        return mDay;
    }

    public void setWeek(String week) {
        mWeek = week;
    }

    public String getWeek() {
        return mWeek;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Logger.trace(TAG);
        dest.writeString(mContent);
        dest.writeString(mImageUrl);
        dest.writeString(mBackgroundImageUrl);
        dest.writeString(mTitle);
        dest.writeString(mMonth);
        dest.writeString(mDay);
        dest.writeString(mWeek);
    }

    private DailyCoverVO(Parcel source) {
        mContent = source.readString();
        mImageUrl = source.readString();
        mBackgroundImageUrl = source.readString();
        mTitle = source.readString();
        mMonth = source.readString();
        mDay = source.readString();
        mWeek = source.readString();
    }

    public static final Parcelable.Creator<DailyCoverVO> CREATOR = new Creator<DailyCoverVO>() {
        @Override
        public DailyCoverVO createFromParcel(Parcel source) {
            Logger.trace(TAG);
            return new DailyCoverVO(source);
        }

        @Override
        public DailyCoverVO[] newArray(int size) {
            return new DailyCoverVO[0];
        }
    };

    @Override
    public String toString() {
        return String.format(Locale.CHINA, "{DailyCoverVO: content = %s, imageUrl = %s, month = %s, day = %s, title = %s}",
            mContent, mImageUrl, mMonth, mDay, mTitle);
    }
}
