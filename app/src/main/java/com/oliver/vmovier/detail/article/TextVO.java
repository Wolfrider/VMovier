package com.oliver.vmovier.detail.article;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.oliver.vmovier.core.Constant.CardType.ArticleDetail;

public class TextVO implements IArticleItemVO {

    private String mText;

    private String mTextAlign;

    private String mTextColor;

    private String mTextSize;

    public TextVO() {
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getTextAlign() {
        return mTextAlign;
    }

    public void setTextAlign(String textAlign) {
        mTextAlign = textAlign;
    }

    public String getTextColor() {
        return mTextColor;
    }

    public void setTextColor(String textColor) {
        if (!TextUtils.isEmpty(textColor)) {
            textColor = "#" + textColor;
        }
        mTextColor = textColor;
    }

    public String getTextSize() {
        return mTextSize;
    }

    public void setTextSize(String textSize) {
        mTextSize = textSize;
    }

    private TextVO(Parcel args) {
        mText = args.readString();
        mTextAlign = args.readString();
        mTextColor = args.readString();
        mTextSize = args.readString();
    }

    @Override
    public int getType() {
        return ArticleDetail.TEXT;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
        dest.writeString(mTextAlign);
        dest.writeString(mTextColor);
        dest.writeString(mTextSize);
    }

    public static final Parcelable.Creator<TextVO> CREATOR = new Creator<TextVO>() {
        @Override
        public TextVO createFromParcel(Parcel source) {
            return new TextVO(source);
        }

        @Override
        public TextVO[] newArray(int size) {
            return new TextVO[0];
        }
    };
}
