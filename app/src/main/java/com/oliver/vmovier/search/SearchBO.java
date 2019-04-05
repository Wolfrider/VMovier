package com.oliver.vmovier.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.dto.CategoryDTO;
import com.oliver.vmovier.core.dto.KeyValueDTO;
import com.oliver.vmovier.core.dto.ListContentDTO;
import com.oliver.vmovier.core.dto.ListItemDTO;
import com.oliver.vmovier.core.dto.RecommendDTO;
import com.oliver.vmovier.core.dto.RecommendWordDTO;
import com.oliver.vmovier.core.dto.SearchDTO;
import com.oliver.vmovier.core.post.PostVO;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class SearchBO {

    private static final String TAG = "SearchBO";

    private class SearchFilter {
        private String mSearchWord = "";
        private String mOrder = "";
        private String mType = "";
        private String mCateId = "";
    }

    private ApiGateway mApiGateway;

    private HashMap<String, String> mOrders;
    private HashMap<String, String> mTypes;
    private HashMap<String, String> mCates;

    private String mNextPageFullUrl;

    private SearchFilter mSearchFilter;

    public SearchBO(@NonNull ApiGateway apiGateway) {
        mApiGateway = apiGateway;
        mOrders = new HashMap<>();
        mTypes = new HashMap<>();
        mCates = new HashMap<>();
        mSearchFilter = new SearchFilter();
    }

    public Single<List<RecommendItemVO>> getRecommend() {
        return mApiGateway.getRecommend().map(new Function<RecommendDTO, List<RecommendItemVO>>() {
            @Override
            public List<RecommendItemVO> apply(RecommendDTO recommendDTO) throws Exception {
                List<RecommendItemVO> recommendItemVOS = convertRecommend(recommendDTO.getRecommend());
                mOrders = convertToHashMap(recommendDTO.getOrder());
                mTypes = convertToHashMap(recommendDTO.getFilter().getTypes());
                for (CategoryDTO categoryDTO : recommendDTO.getFilter().getCates()) {
                    mCates.put(categoryDTO.getName(), categoryDTO.getId());
                }
                mSearchFilter.mOrder = recommendDTO.getOrder().get(0).getValue();
                mSearchFilter.mType = recommendDTO.getFilter().getTypes().get(0).getValue();
                return recommendItemVOS;
            }
        });
    }

    public Single<List<IRVItemVO>> getSearch(String word) {
        mSearchFilter.mSearchWord = word;
        return mApiGateway.getSearch(word).map(new Function<SearchDTO, List<IRVItemVO>>() {
            @Override
            public List<IRVItemVO> apply(SearchDTO searchDTO) throws Exception {
                mNextPageFullUrl = searchDTO.getResult().getNextPageFullUrl();
                return convertPostItems(searchDTO.getResult());
            }
        });
    }

    public boolean canMoreSearch() {
        return !TextUtils.isEmpty(mNextPageFullUrl);
    }

    public Single<List<IRVItemVO>> getMoreSearch() {
        return mApiGateway.getMoreSearch(mNextPageFullUrl).map(new Function<SearchDTO, List<IRVItemVO>>() {
            @Override
            public List<IRVItemVO> apply(SearchDTO searchDTO) throws Exception {
                mNextPageFullUrl = searchDTO.getResult().getNextPageFullUrl();
                return convertPostItems(searchDTO.getResult());
            }
        });
    }

    public Single<List<IRVItemVO>> getSearchByFilter(String value, int position) {
        updateFilter(value, position);
        return mApiGateway.getSearchByFilter(mSearchFilter.mSearchWord, mSearchFilter.mType, mSearchFilter.mOrder, mSearchFilter.mCateId).map(
            new Function<SearchDTO, List<IRVItemVO>>() {
                @Override
                public List<IRVItemVO> apply(SearchDTO searchDTO) throws Exception {
                    mNextPageFullUrl = searchDTO.getResult().getNextPageFullUrl();
                    return convertPostItems(searchDTO.getResult());
                }
            });
    }

    public List<FilterVO> getOrders() {
        return convertFilterVO(mOrders.keySet());
    }

    public List<FilterVO> getTypes() {
        return convertFilterVO(mTypes.keySet());
    }

    public List<FilterVO> getCates() {
        List<FilterVO> filterVOS = convertFilterVO(mCates.keySet());
        FilterVO filterVO = new FilterVO();
        filterVO.setName("所有视频");
        filterVOS.add(0, filterVO);
        return filterVOS;
    }

    private List<RecommendItemVO> convertRecommend(List<RecommendWordDTO> recommendWordDTOS) {
        List<RecommendItemVO> recommendItemVOS = new ArrayList<>(16);
        for (RecommendWordDTO wordDTO : recommendWordDTOS) {
            RecommendItemVO itemVO = new RecommendItemVO();
            itemVO.setWord(wordDTO.getWord());
            recommendItemVOS.add(itemVO);
        }
        return recommendItemVOS;
    }

    private HashMap<String, String> convertToHashMap(List<KeyValueDTO> keyValueDTOS) {
        HashMap<String, String> types = new HashMap<>(16);
        for (KeyValueDTO type : keyValueDTOS) {
            types.put(type.getName(), type.getValue());
        }
        return types;
    }

    private List<IRVItemVO> convertPostItems(ListContentDTO<ListItemDTO> listContentDTO) {
        List<IRVItemVO> itemVOS = new ArrayList<>(listContentDTO.getArrayData().size() + 1);
        for (ListItemDTO itemDTO: listContentDTO.getArrayData()) {
            PostVO postVO = new PostVO();
            postVO.setId(itemDTO.getId());
            postVO.setTitle(itemDTO.getTitle());
            postVO.setSubTitle(itemDTO.getCategoryList().get(0).getName(), itemDTO.getDuration());
            postVO.setImageUrl(itemDTO.getImageUrl());
            itemVOS.add(postVO);
        }
        return itemVOS;
    }

    private List<FilterVO> convertFilterVO(Set<String> items) {
        List<FilterVO> itemVOS = new ArrayList<>(16);
        for (String item : items) {
            FilterVO filterVO = new FilterVO();
            filterVO.setName(item);
            itemVOS.add(filterVO);
        }
        return itemVOS;
    }

    private void updateFilter(String value, int position) {
        switch (position) {
            case 0:
                mSearchFilter.mType = mTypes.get(value);
                break;
            case 1:
                mSearchFilter.mOrder = mOrders.get(value);
                break;
            case 2:
                if (mCates.containsKey(value)) {
                    mSearchFilter.mCateId = mCates.get(value);
                } else {
                    mSearchFilter.mCateId = "";
                }
                break;
            default:
                break;
        }
    }

}
