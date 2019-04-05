package com.oliver.vmovier.search;

import java.util.List;
import java.util.Set;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.dto.RecommendDTO;
import com.oliver.vmovier.core.dto.RecommendWordDTO;
import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.functions.Consumer;

public class SearchViewModel extends BaseRxViewModel {

    private MutableLiveData<List<RecommendItemVO>> mRecommendWords;
    private MutableLiveData<List<IRVItemVO>> mSearchResult;

    private SearchBO mSearchBO;
    private boolean mSearching;

    public SearchViewModel() {
        super();
        mRecommendWords = new MutableLiveData<>();
        mSearchResult = new MutableLiveData<>();
        mSearchBO = ObjectProviders.get(SearchBO.class);
        init();
    }

    public LiveData<List<RecommendItemVO>> getRecommend() {
        return mRecommendWords;
    }

    public LiveData<List<IRVItemVO>> getResult() {
        return mSearchResult;
    }

    public void getSearch(final String tag) {
        if (!mSearching) {
            mRxRes.append(mSearchBO.getSearch(tag).subscribe(new Consumer<List<IRVItemVO>>() {
                @Override
                public void accept(List<IRVItemVO> itemVOS) throws Exception {
                    mSearching = false;
                    mSearchResult.postValue(itemVOS);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    mSearching = false;
                }
            }));
            mSearching = true;
        }
    }

    public boolean canMoreSearch() {
        return mSearchBO.canMoreSearch();
    }

    public void getMoreSearch() {
        if (!mSearching) {
            mRxRes.append(mSearchBO.getMoreSearch().subscribe(new Consumer<List<IRVItemVO>>() {
                @Override
                public void accept(List<IRVItemVO> itemVOS) throws Exception {
                    mSearching = false;
                    mSearchResult.postValue(itemVOS);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    mSearching = false;
                }
            }));
            mSearching = true;
        }
    }

    public void getSearchByFilter(String value, int position) {
        if (!mSearching) {
            mRxRes.append(mSearchBO.getSearchByFilter(value, position).subscribe(new Consumer<List<IRVItemVO>>() {
                @Override
                public void accept(List<IRVItemVO> itemVOS) throws Exception {
                    mSearching = false;
                    mSearchResult.postValue(itemVOS);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    mSearching = false;
                }
            }));
            mSearching = true;
        }
    }

    public List<FilterVO> getTypes() {
        return mSearchBO.getTypes();
    }

    public List<FilterVO> getOrders() {
        return mSearchBO.getOrders();
    }

    public List<FilterVO> getCates() {
        return mSearchBO.getCates();
    }



    private void init() {
        mRxRes.append(mSearchBO.getRecommend().subscribe(new Consumer<List<RecommendItemVO>>() {
            @Override
            public void accept(List<RecommendItemVO> recommendItemVOS) throws Exception {
                mRecommendWords.postValue(recommendItemVOS);
            }
        }));
    }
}
