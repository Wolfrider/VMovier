package com.oliver.vmovier.home.daily;

import java.util.Calendar;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.functions.Consumer;

public class DailyCoverViewModel extends BaseRxViewModel {

    private static final String TAG = "DailyCoverViewModel";

    private DailyCoverBO mDailyCoverBO;
    private MutableLiveData<Boolean> mUnRead;
    private MutableLiveData<DailyCoverVO> mData;

    public DailyCoverViewModel() {
        super();
        mDailyCoverBO = ObjectProviders.get(DailyCoverBO.class);
        mData = new MutableLiveData<>();
        mUnRead = new MutableLiveData<>();
        load();
    }

    public int getToday() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    @NonNull
    public LiveData<Boolean> isUnRead() {
        return mUnRead;
    }

    @NonNull
    public LiveData<DailyCoverVO> getData() {
        return mData;
    }

    public void read() {
        mDailyCoverBO.read();
    }

    private void load() {
        mRxRes.append(mDailyCoverBO.fetch().subscribe(new Consumer<DailyCoverVO>() {
            @Override
            public void accept(DailyCoverVO dailyCoverVO) throws Exception {
                Logger.d(TAG, "setData " + dailyCoverVO);
                mData.postValue(dailyCoverVO);
            }
        }));
        mRxRes.append(mDailyCoverBO.hasRead().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                mUnRead.postValue(!aBoolean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mUnRead.postValue(true);
            }
        }));
    }

}
