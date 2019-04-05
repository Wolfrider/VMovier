package com.oliver.vmovier.search;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType;

public class FilterVO implements IRVItemVO {

    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int getType() {
        return CardType.FILTER;
    }
}
