package com.oliver.vmovier.home.daily;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.FutureTask;

import android.support.annotation.NonNull;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.async.AsyncExecutor;
import com.oliver.vmovier.core.db.DailyCoverEntity;
import com.oliver.vmovier.core.db.VMovierDB;
import com.oliver.vmovier.core.dto.DailyCoverDTO;
import com.oliver.vmovier.core.dto.DataDTO;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class DailyCoverBO {

    private static final String TAG = "DailyCoverBO";

    private ApiGateway mApiGateway;
    private DailyCoverVO mData;
    private String mCurrentDate;

    public DailyCoverBO(@NonNull ApiGateway apiGateway) {
        mApiGateway = apiGateway;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
        mCurrentDate = dateFormat.format(new Date());
    }

    public Single<DailyCoverVO> fetch() {
        return mApiGateway.getDailyCover().map(new Function<DailyCoverDTO, DailyCoverVO>() {
            @Override
            public DailyCoverVO apply(DailyCoverDTO dailyCoverDTO) throws Exception {
                Logger.d(TAG, "fetch, " + dailyCoverDTO);
                mData = new DailyCoverVO();
                mData.setContent(dailyCoverDTO.getContent());
                mData.setTitle(dailyCoverDTO.getTitle());
                mData.setBackgroundImageUrl(dailyCoverDTO.getBlurredImageUrl());
                mData.setImageUrl(dailyCoverDTO.getImageUrl());
                mData.setMonth(dailyCoverDTO.getTime().getMonth());
                mData.setDay(dailyCoverDTO.getTime().getDay());
                mData.setWeek(dailyCoverDTO.getTime().getWeek());
                return mData;
            }
        });
    }

    public Single<Boolean> hasRead() {
        Logger.d(TAG, "hasRead " + mCurrentDate);
        return VMovierDB.getDB().getDailyCoverDao().findByDate(mCurrentDate)
            .map(new Function<DailyCoverEntity, Boolean>() {
                @Override
                public Boolean apply(DailyCoverEntity entity) throws Exception {
                    return null != entity;
                }
            }).subscribeOn(Schedulers.io());
    }

    public void read() {
        Logger.d(TAG, "read " + mCurrentDate);
        AsyncExecutor.submit(new Runnable() {
            @Override
            public void run() {
                DailyCoverEntity entity = new DailyCoverEntity();
                entity.setDate(mCurrentDate);
                VMovierDB.getDB().getDailyCoverDao().insert(entity);
            }
        });
    }
}
