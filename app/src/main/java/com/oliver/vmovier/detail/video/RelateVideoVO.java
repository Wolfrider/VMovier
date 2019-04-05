package com.oliver.vmovier.detail.video;

import java.util.ArrayList;
import java.util.List;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType.VideoDetail;
import com.oliver.vmovier.core.post.PostVO;

public class RelateVideoVO implements IRVItemVO {

    private String mName;

    private String mScheme;

    private List<PostVO> mRelateList;

    public RelateVideoVO() {
        mRelateList = new ArrayList<>(16);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getScheme() {
        return mScheme;
    }

    public void setScheme(String scheme) {
        mScheme = scheme;
    }

    public List<PostVO> getRelateList() {
        return mRelateList;
    }

    @Override
    public int getType() {
        return VideoDetail.RELATED;
    }
}
