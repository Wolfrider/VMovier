package com.oliver.vmovier.core.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class DataDTO<TData> {

    @JSONField(name = "data")
    private TData mData;

    @JSONField(name = "status")
    private String mStatus;

    @JSONField(name = "msg")
    private String mMsg;

    @JSONField(name = "data")
    public TData getData() {
        return mData;
    }

    @JSONField(name = "data")
    public void setData(TData data) {
        mData = data;
    }

    @JSONField(name = "status")
    public String getStatus() {
        return mStatus;
    }

    @JSONField(name = "status")
    public void setStatus(String status) {
        mStatus = status;
    }

    @JSONField(name = "msg")
    public String getMsg() {
        return mMsg;
    }

    @JSONField(name = "msg")
    public void setMsg(String msg) {
        mMsg = msg;
    }
}
