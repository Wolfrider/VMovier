package com.oliver.vmovier.channel;

import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType;

public class ChannelVO implements IRVItemVO, Parcelable {

    private String mName;

    private String mIconUrl;

    private int mCateType;

    private String mCateName;

    private String mCateNav;

    public ChannelVO() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = String.format(Locale.CHINA, "#%s#", name);
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public int getCateType() {
        return mCateType;
    }

    public void setCateType(int type) {
        mCateType = type;
    }

    public String getCateName() {
        return mCateName;
    }

    public void setCateName(String name) {
        mCateName = name;
    }

    public String getCateNav() {
        return mCateNav;
    }

    public void setCateNav(String nav) {
        mCateNav = nav;
    }

    @Override
    public int getType() {
        return CardType.CHANNEL;
    }

    private ChannelVO(Parcel source) {
        mName = source.readString();
        mIconUrl = source.readString();
        mCateType = source.readInt();
        mCateName = source.readString();
        mCateNav = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mIconUrl);
        dest.writeInt(mCateType);
        dest.writeString(mCateName);
        dest.writeString(mCateNav);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<ChannelVO>() {
        @Override
        public ChannelVO createFromParcel(Parcel source) {
            return new ChannelVO(source);
        }

        @Override
        public ChannelVO[] newArray(int size) {
            return new ChannelVO[0];
        }
    };

    @Override
    public String toString() {
        return String.format(Locale.US, "{ChannelVO: name = %s, iconUrl = %s, cateNav = %s}",
            mName, mIconUrl, mCateNav);
    }
}
