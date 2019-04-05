package com.oliver.vmovier.detail.article;

import java.util.ArrayList;

import android.os.Bundle;

public class ArticleVO {

    private String mTitle;

    private ArrayList<IArticleItemVO> mContents;

    public ArticleVO() {
        mContents = new ArrayList<>(16);
    }

    public ArticleVO(Bundle args) {
        mTitle = args.getString("mTitle");
        mContents = args.getParcelableArrayList("mContents");
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("mTitle", mTitle);
        bundle.putParcelableArrayList("mContents", mContents);
        return bundle;
    }

    public ArrayList<IArticleItemVO> getContents() {
        return mContents;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
