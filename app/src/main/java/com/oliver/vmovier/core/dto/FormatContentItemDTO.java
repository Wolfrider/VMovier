package com.oliver.vmovier.core.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class FormatContentItemDTO implements Serializable {

    @JSONField(name = "attr")
    private TextAttrDTO mTextAttrDTO;

    @JSONField(name = "content")
    private String mContent;

    @JSONField(name = "type")
    private String mType;

    @JSONField(name = "attr")
    public TextAttrDTO getTextAttrDTO() {
        return mTextAttrDTO;
    }

    @JSONField(name = "attr")
    public void setTextAttrDTO(TextAttrDTO textAttrDTO) {
        mTextAttrDTO = textAttrDTO;
    }

    @JSONField(name = "content")
    public String getContent() {
        return mContent;
    }

    @JSONField(name = "content")
    public void setContent(String content) {
        mContent = content;
    }

    @JSONField(name = "type")
    public String getType() {
        return mType;
    }

    @JSONField(name = "type")
    public void setType(String type) {
        mType = type;
    }
}
