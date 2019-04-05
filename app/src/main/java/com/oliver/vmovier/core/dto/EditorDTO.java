package com.oliver.vmovier.core.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class EditorDTO {

    @JSONField(name = "uid")
    private String mId;

    @JSONField(name = "username")
    private String mAuthor;

    @JSONField(name = "avatar")
    private String mAvatarUrl;

    @JSONField(name = "uid")
    public String getId() {
        return mId;
    }

    @JSONField(name = "uid")
    public void setId(String id) {
        mId = id;
    }

    @JSONField(name = "username")
    public String getAuthor() {
        return mAuthor;
    }

    @JSONField(name = "username")
    public void setAuthor(String author) {
        mAuthor = author;
    }

    @JSONField(name = "avatar")
    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    @JSONField(name = "avatar")
    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }
}
