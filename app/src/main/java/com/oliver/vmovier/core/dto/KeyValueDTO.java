package com.oliver.vmovier.core.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class KeyValueDTO {

    @JSONField(name = "key")
    private String mName;

    @JSONField(name = "value")
    private String mValue;

    @JSONField(name = "key")
    public String getName() {
        return mName;
    }

    @JSONField(name = "key")
    public void setName(String name) {
        mName = name;
    }

    @JSONField(name = "value")
    public String getValue() {
        return mValue;
    }

    @JSONField(name = "value")
    public void setValue(String value) {
        mValue = value;
    }
}
