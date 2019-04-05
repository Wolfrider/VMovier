package com.oliver.vmovier.channel;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.functions.Consumer;

public class ChannelViewModel extends BaseRxViewModel {

    private static final String TAG = "ChannelViewModel";

    private ChannelBO mChannelBO;
    private MutableLiveData<List<ChannelVO>> mData;

    public ChannelViewModel() {
        super();
        mChannelBO = ObjectProviders.get(ChannelBO.class);
        mData = new MutableLiveData<>();
        load();
    }

    public LiveData<List<ChannelVO>> getData() {
        return mData;
    }

    private void load() {
        mRxRes.append(mChannelBO.fetch().subscribe(new Consumer<List<ChannelVO>>() {
            @Override
            public void accept(List<ChannelVO> channelVOList) throws Exception {
                mData.postValue(channelVOList);
            }
        }));
    }

}
