package com.oliver.vmovier.discovery;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType.Discovery;

public class TitleItemVO implements IRVItemVO {

    private String mTitle;

    private String mScheme;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getScheme() {
        return mScheme;
    }

    public void setScheme(String scheme) {
        this.mScheme = scheme;
    }

    @Override
    public int getType() {
        return Discovery.TITLE;
    }
}
