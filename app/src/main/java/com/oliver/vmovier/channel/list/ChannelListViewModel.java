package com.oliver.vmovier.channel.list;

import java.util.LinkedList;
import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.channel.ChannelVO;
import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.functions.Consumer;

public class ChannelListViewModel extends BaseRxViewModel {

    private static final String TAG = "ChannelListViewModel";

    private ChannelListBO mChannelListBO;
    private MutableLiveData<List<PostVO>> mPostData;
    private List<PostVO> mRawPostData;
    private String mTitle;

    public ChannelListViewModel(@NonNull ChannelVO channelVO) {
        super();
        mPostData = new MutableLiveData<>();
        mChannelListBO = ObjectProviders.get(ChannelListBO.class);
        mChannelListBO.init(channelVO);
        mRawPostData = new LinkedList<>();
        mTitle = channelVO.getCateName();
        //refresh();
    }

    public LiveData<List<PostVO>> getPostData() {
        return mPostData;
    }

    public String getTitle() {
        return mTitle;
    }

    public void refresh() {
        Logger.d(TAG, "refresh");
        mRxRes.append(mChannelListBO.fetch().subscribe(new Consumer<List<PostVO>>() {
            @Override
            public void accept(List<PostVO> postVOS) throws Exception {
                mRawPostData.addAll(0, postVOS);
                mPostData.postValue(postVOS);
            }
        }));
    }

    public void load() {
        Logger.d(TAG, "load");
        mRxRes.append(mChannelListBO.fetch().subscribe(new Consumer<List<PostVO>>() {
            @Override
            public void accept(List<PostVO> postVOS) throws Exception {
                mRawPostData.addAll(postVOS);
                mPostData.postValue(postVOS);
            }
        }));
    }
}
