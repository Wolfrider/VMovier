package com.oliver.vmovier.discovery;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.guice.ObjectProviders;
import io.reactivex.functions.Consumer;

public class DiscoveryViewModel extends BaseRxViewModel {

    private DiscoveryBO mDiscoveryBO;
    private MutableLiveData<List<IRVItemVO>> mData;

    public DiscoveryViewModel() {
        super();
        mDiscoveryBO = ObjectProviders.get(DiscoveryBO.class);
        mData = new MutableLiveData<>();
        refresh();
    }

    public void refresh() {
        mRxRes.append(mDiscoveryBO.refresh().subscribe(new Consumer<List<IRVItemVO>>() {
            @Override
            public void accept(List<IRVItemVO> baseDiscoveryVOS) throws Exception {
                mData.postValue(baseDiscoveryVOS);
            }
        }));
    }

    public void loadMore() {
        mRxRes.append(mDiscoveryBO.loadMore().subscribe(new Consumer<List<IRVItemVO>>() {
            @Override
            public void accept(List<IRVItemVO> baseDiscoveryVOS) throws Exception {
                List<IRVItemVO> data = mData.getValue();
                if (null != data) {
                    data.addAll(baseDiscoveryVOS);
                    mData.postValue(baseDiscoveryVOS);
                }
            }
        }));
    }

    public LiveData<List<IRVItemVO>> getData() {
        return mData;
    }

}
