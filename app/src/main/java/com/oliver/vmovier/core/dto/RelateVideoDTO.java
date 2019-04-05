package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class RelateVideoDTO {

    @JSONField(name = "id")
    private String mId;

    @JSONField(name = "name")
    private String mName;

    @JSONField(name = "scheme")
    private String mScheme;

    @JSONField(name = "list")
    private List<PostDTO> mPostList;

    @JSONField(name = "id")
    public String getId() {
        return mId;
    }

    @JSONField(name = "id")
    public void setId(String id) {
        mId = id;
    }

    @JSONField(name = "name")
    public String getName() {
        return mName;
    }

    @JSONField(name = "name")
    public void setName(String name) {
        mName = name;
    }

    @JSONField(name = "scheme")
    public String getScheme() {
        return mScheme;
    }

    @JSONField(name = "scheme")
    public void setScheme(String scheme) {
        mScheme = scheme;
    }

    @JSONField(name = "list")
    public List<PostDTO> getPostList() {
        return mPostList;
    }

    @JSONField(name = "list")
    public void setPostList(List<PostDTO> postList) {
        mPostList = postList;
    }
}
