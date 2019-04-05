package com.oliver.vmovier.discovery;

import java.util.ArrayList;
import java.util.List;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType.Discovery;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.dto.BannerDTO;
import com.oliver.vmovier.core.dto.ListContentDTO;
import com.oliver.vmovier.core.dto.ListDTO;
import com.oliver.vmovier.core.dto.ListItemDTO;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class DiscoveryBO {

    private static final String TAG = "DiscoveryBO";

    private ApiGateway mApiGateway;

    private String mNextPageUrl;
    private boolean mIsLoading;

    public DiscoveryBO(@NonNull ApiGateway apiGateway) {
        mApiGateway = apiGateway;
    }

    public Single<List<IRVItemVO>> refresh() {
        mIsLoading = true;
        return mApiGateway.getFirstDiscovery().map(new Function<ListDTO, List<IRVItemVO>>() {
            @Override
            public List<IRVItemVO> apply(ListDTO listDTO) throws Exception {
                List<IRVItemVO> content = new ArrayList<>(32);
                content.add(convertBanner(listDTO.getBanner()));
                content.addAll(convertPost(listDTO.getToday()));
                content.addAll(convertHot(listDTO.getHot()));
                content.addAll(convertAlbum(listDTO.getAlum()));
                content.addAll(convertPost(listDTO.getPost()));
                mNextPageUrl = listDTO.getPost().getNextPageUrl();
                mIsLoading = false;
                Logger.d(TAG, "getFirstDiscovery done");
                return content;
            }
        });
    }

    public Single<List<IRVItemVO>> loadMore() {
        if (canLoadMore()) {
            mIsLoading = true;
            Logger.d(TAG, "loadMore " + mNextPageUrl);
            return mApiGateway.getNextPage(mNextPageUrl).map(
                new Function<ListContentDTO<ListItemDTO>, List<IRVItemVO>>() {
                    @Override
                    public List<IRVItemVO> apply(ListContentDTO<ListItemDTO> listContentDTO) throws Exception {
                        List<IRVItemVO> posts = convertPost(listContentDTO);
                        mNextPageUrl = listContentDTO.getNextPageUrl();
                        mIsLoading = false;
                        return posts;
                    }
                });
        }
        return Single.fromObservable(Observable.<List<IRVItemVO>>empty());
    }

    private boolean canLoadMore() {
        return !TextUtils.isEmpty(mNextPageUrl) && !mIsLoading;
    }

    private IRVItemVO convertBanner(ListContentDTO<BannerDTO> listContentDTO) {
        CompositeVO compositeVO = new CompositeVO();
        Logger.d(TAG, "convertBanner listContentDTO size = " + listContentDTO.getArrayData().size());
        for (BannerDTO bannerDTO: listContentDTO.getArrayData()) {
            BannerItemVO bannerItemVO = new BannerItemVO();
            bannerItemVO.setId(bannerDTO.getId());
            bannerItemVO.setImageUrl(bannerDTO.getImageUrl());
            bannerItemVO.setParam(bannerDTO.getExtra().getParam());
            compositeVO.addItem(bannerItemVO);
            Logger.d(TAG, "convertBanner addItem = " + bannerItemVO.toString());
        }
        return compositeVO;
    }

    private List<IRVItemVO> convertHot(ListContentDTO<ListItemDTO> listContentDTO) {
        List<IRVItemVO> hot = new ArrayList<>(3);
        hot.add(convertTitle(listContentDTO));
        hot.add(convertPostItem(listContentDTO.getArrayData().get(0)));
        CompositeVO compositeVO = new CompositeVO();
        for (int i = 1; i < listContentDTO.getArrayData().size(); ++i) {
            compositeVO.addItem(convertPostItem(listContentDTO.getArrayData().get(i)));
        }
        compositeVO.setType(Discovery.GRID_POST);
        hot.add(compositeVO);
        return hot;
    }

    private List<IRVItemVO> convertAlbum(ListContentDTO<ListItemDTO> listContentDTO) {
        List<IRVItemVO> albums = new ArrayList<>(2);
        albums.add(convertTitle(listContentDTO));
        CompositeVO compositeVO = new CompositeVO();
        for (ListItemDTO itemDTO: listContentDTO.getArrayData()) {
            AlbumItemVO itemVO = new AlbumItemVO();
            itemVO.setId(itemDTO.getId());
            itemVO.setTitle(itemDTO.getTitle());
            itemVO.setSubTitle(itemDTO.getSubTitle());
            itemVO.setRequestUrl(itemDTO.getRequestUrl());
            itemVO.setImageUrl(itemDTO.getImageUrl());
            compositeVO.addItem(itemVO);
        }
        albums.add(compositeVO);
        return albums;
    }

    private List<IRVItemVO> convertPost(ListContentDTO<ListItemDTO> listContentDTO) {
        List<IRVItemVO> todayList = new ArrayList<>(listContentDTO.getArrayData().size() + 1);
        todayList.add(convertTitle(listContentDTO));
        for (ListItemDTO itemDTO: listContentDTO.getArrayData()) {
            todayList.add(convertPostItem(itemDTO));
        }
        return todayList;
    }

    private PostVO convertPostItem(ListItemDTO itemDTO) {
        PostVO postVO = new PostVO();
        postVO.setId(itemDTO.getId());
        postVO.setTitle(itemDTO.getTitle());
        postVO.setSubTitle(itemDTO.getCategoryList().get(0).getName(), itemDTO.getDuration());
        postVO.setImageUrl(itemDTO.getImageUrl());
        return postVO;
    }

    private TitleItemVO convertTitle(ListContentDTO<ListItemDTO> listContentDTO) {
        TitleItemVO titleItemVO = new TitleItemVO();
        titleItemVO.setTitle(listContentDTO.getTitle());
        titleItemVO.setScheme(listContentDTO.getScheme());
        return titleItemVO;
    }

}
