package com.oliver.vmovier.core.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class RecommendWordDTO {

    @JSONField(name = "id")
    private String mId;

    @JSONField(name = "kw")
    private String mWord;

    @JSONField(name = "id")
    public String getId() {
        return mId;
    }

    @JSONField(name = "id")
    public void setId(String id) {
        mId = id;
    }

    @JSONField(name = "kw")
    public String getWord() {
        return mWord;
    }

    @JSONField(name = "kw")
    public void setWord(String word) {
        mWord = word;
    }
}
