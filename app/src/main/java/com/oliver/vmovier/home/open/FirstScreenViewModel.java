package com.oliver.vmovier.home.open;

import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.core.dto.FirstScreenDTO;
import com.oliver.vmovier.core.guice.ObjectProviders;

public class FirstScreenViewModel extends BaseRxViewModel {

    private FirstScreenBO mFirstScreenBO;

    public FirstScreenViewModel() {
        super();
        mFirstScreenBO = ObjectProviders.get(FirstScreenBO.class);
    }

    public FirstScreenVO getData() {
        FirstScreenDTO dto = mFirstScreenBO.getLocalData();
        if (null != dto) {
            FirstScreenVO firstScreenVO = new FirstScreenVO();
            firstScreenVO.setClickType(dto.getClickType());
            firstScreenVO.setContent(dto.getContent());
            firstScreenVO.setDuration(dto.getDuration());
            firstScreenVO.setImagePath(dto.getImageUrl());
            return firstScreenVO;
        }
        return null;
    }

    public void finish() {
        mRxRes.append(mFirstScreenBO.fetchRemote());
    }
}
