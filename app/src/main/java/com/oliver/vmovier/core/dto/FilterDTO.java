package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class FilterDTO {

    @JSONField(name = "cate")
    private List<CategoryDTO> mCates;

    @JSONField(name = "type")
    private List<KeyValueDTO> mTypes;

    @JSONField(name = "cate")
    public List<CategoryDTO> getCates() {
        return mCates;
    }

    @JSONField(name = "cate")
    public void setCates(List<CategoryDTO> cates) {
        mCates = cates;
    }

    @JSONField(name = "type")
    public List<KeyValueDTO> getTypes() {
        return mTypes;
    }

    @JSONField(name = "type")
    public void setTypes(List<KeyValueDTO> types) {
        mTypes = types;
    }
}
