package com.oliver.vmovier.detail.video;

import java.util.Locale;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType.VideoDetail;
import com.oliver.vmovier.detail.article.ArticleVO;

public class IntroVO implements IRVItemVO {

    private String mTitle;

    private String mSubTitle;

    private String mContent;

    private ArticleVO mArticleVO;

    public IntroVO() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String cateName, int duration) {
        mSubTitle = String.format(Locale.CHINA, "%s / %02d'%02d\"", cateName, duration/60, duration%60);
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public ArticleVO getArticleVO() {
        return mArticleVO;
    }

    public void setArticleVO(ArticleVO articleVO) {
        mArticleVO = articleVO;
    }

    @Override
    public int getType() {
        return VideoDetail.INTRO;
    }
}
