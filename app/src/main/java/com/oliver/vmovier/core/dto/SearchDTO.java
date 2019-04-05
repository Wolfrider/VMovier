package com.oliver.vmovier.core.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SearchDTO {

    @JSONField(name = "filter")
    private FilterDTO mFilter;

    @JSONField(name = "order")
    private List<KeyValueDTO> mOrder;

    @JSONField(name = "recommend_keywords")
    private List<RecommendWordDTO> mRecommend;

    @JSONField(name = "result")
    private ListContentDTO<ListItemDTO> mResult;

    @JSONField(name = "filter")
    public FilterDTO getFilter() {
        return mFilter;
    }

    @JSONField(name = "filter")
    public void setFilter(FilterDTO filter) {
        mFilter = filter;
    }

    @JSONField(name = "order")
    public List<KeyValueDTO> getOrder() {
        return mOrder;
    }

    @JSONField(name = "order")
    public void setOrder(List<KeyValueDTO> order) {
        mOrder = order;
    }

    @JSONField(name = "recommend_keywords")
    public List<RecommendWordDTO> getRecommend() {
        return mRecommend;
    }

    @JSONField(name = "recommend_keywords")
    public void setRecommand(List<RecommendWordDTO> recommend) {
        mRecommend = recommend;
    }

    @JSONField(name = "result")
    public ListContentDTO<ListItemDTO> getResult() {
        return mResult;
    }

    @JSONField(name = "result")
    public void setResult(ListContentDTO<ListItemDTO> result) {
        mResult = result;
    }
}
