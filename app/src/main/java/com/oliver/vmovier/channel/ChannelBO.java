package com.oliver.vmovier.channel;

import java.util.LinkedList;
import java.util.List;

import android.support.annotation.NonNull;
import com.oliver.vmovier.core.Constant.CateType;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.dto.CategoryDTO;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ChannelBO {

    private static final String TAG = "ChannelBO";

    private ApiGateway mApiGateway;

    public ChannelBO(@NonNull ApiGateway apiGateway) {
        mApiGateway = apiGateway;
    }

    public Single<List<ChannelVO>> fetch() {
        Logger.trace(TAG);
        return mApiGateway.getCategory().map(
            new Function<List<CategoryDTO>, List<ChannelVO>>() {
                @Override
                public List<ChannelVO> apply(List<CategoryDTO> categoryDTOList) throws Exception {
                    List<ChannelVO> channelVOList = new LinkedList<>();
                    for (CategoryDTO categoryDTO: categoryDTOList) {
                        ChannelVO channelVO = new ChannelVO();
                        channelVO.setName(categoryDTO.getName());
                        channelVO.setIconUrl(categoryDTO.getIconUrl());
                        channelVO.setCateName(categoryDTO.getName());
                        channelVO.setCateType(categoryDTO.getType());
                        switch (categoryDTO.getType()) {
                            case CateType.CATE:
                                channelVO.setCateNav(categoryDTO.getId());
                                break;
                            case CateType.TAB:
                                channelVO.setCateNav(categoryDTO.getTab());
                                break;
                            case CateType.LINK:
                                channelVO.setCateNav(categoryDTO.getLink());
                                break;
                            default:
                                break;
                        }
                        Logger.d(TAG, channelVO.toString());
                        channelVOList.add(channelVO);
                    }
                    return channelVOList;
                }
            });
    }
}
