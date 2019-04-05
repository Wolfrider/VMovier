package com.oliver.vmovier.core.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfoDTO {

    @JSONField(name = "username")
    private String mName;

    @JSONField(name = "avatar")
    private String mAvatarUrl;

    @JSONField(name = "username")
    public String getName() {
        return mName;
    }

    @JSONField(name = "username")
    public void setName(String name) {
        mName = name;
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
