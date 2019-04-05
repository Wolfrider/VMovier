package com.oliver.vmovier.channel.list;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.support.annotation.NonNull;
import com.oliver.vmovier.channel.ChannelVO;
import com.oliver.vmovier.core.Constant.CateType;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.dto.PostDTO;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ChannelListBO {

    private static final String TAG = "ChannelListBO";

    private int mPageNo;
    private ChannelVO mChannelVO;
    private ApiGateway mApiGateway;

    public ChannelListBO(@NonNull ApiGateway apiGateway) {
        mApiGateway = apiGateway;
    }

    public void init(@NonNull ChannelVO channelVO) {
        mChannelVO = channelVO;
        mPageNo = 1;
    }

    public Single<List<PostVO>> fetch() {
        Single<List<PostDTO>> result;
        if (mChannelVO.getCateType() == CateType.TAB) {
            result = mApiGateway.getPostByTab(mChannelVO.getCateNav(), mPageNo);
        } else if (mChannelVO.getCateType() == CateType.CATE) {
            result = mApiGateway.getPostByCate(mChannelVO.getCateNav(), mPageNo);
        } else {
            result = mApiGateway.getPostByTag(mChannelVO.getCateNav(), mPageNo);
        }
        return result.map(new Function<List<PostDTO>, List<PostVO>>() {
            @Override
            public List<PostVO> apply(List<PostDTO> postDTOList) throws Exception {
                Logger.d(TAG, "fetch mapTo, size = " + postDTOList.size());
                List<PostVO> postVOList = new LinkedList<>();
                for (PostDTO dto: postDTOList) {
                    PostVO postVO = new PostVO();
                    postVO.setId(dto.getPostId());
                    postVO.setTitle(dto.getTitle());
                    Logger.d(TAG, "fetch mapTo, getCategory = " + dto.getCategory());
                    postVO.setSubTitle(dto.getCategory().get(0).getName(), dto.getDuration());
                    postVO.setImageUrl(dto.getImageUrl());
                    postVOList.add(postVO);
                }
                Logger.d(TAG, String.format(Locale.US, "fetch, name = %s pageNo = %d result size = %d",
                    mChannelVO.getCateName(), mPageNo, postVOList.size()));
                mPageNo++;
                return postVOList;
            }
        });
    }
}
