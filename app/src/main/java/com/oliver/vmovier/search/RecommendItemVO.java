package com.oliver.vmovier.search;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType;

public class RecommendItemVO implements IRVItemVO {

    private String mWord;

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    @Override
    public int getType() {
        return CardType.RECOMMEND_WORD;
    }
}
