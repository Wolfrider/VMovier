package com.oliver.vmovier.discovery;

import java.util.ArrayList;
import java.util.List;

import com.oliver.vmovier.base.recyclerview.IRVItemVO;

public class CompositeVO implements IRVItemVO {

    private List<IRVItemVO> mItems;
    private Integer mType;

    public CompositeVO() {
        mItems = new ArrayList<>(16);
    }

    public void addItem(IRVItemVO item) {
        mItems.add(item);
    }

    public List<IRVItemVO> getItems() {
        return mItems;
    }

    public void setType(Integer type) {
        mType = type;
    }

    @Override
    public int getType() {
        if (null != mType) {
            return mType;
        } else if (!mItems.isEmpty()) {
            return mItems.get(0).getType();
        }
        throw new IllegalStateException("has no items");
    }
}
