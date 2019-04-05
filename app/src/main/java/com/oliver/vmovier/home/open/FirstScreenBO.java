package com.oliver.vmovier.home.open;

import java.util.Locale;

import android.support.annotation.NonNull;
import com.oliver.vmovier.R;
import com.oliver.vmovier.core.Info.InfoBus;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.dto.DataDTO;
import com.oliver.vmovier.core.dto.FirstScreenDTO;
import com.oliver.vmovier.core.local.LocalRepository;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class FirstScreenBO {

    private static final String TAG = "FirstScreenBO";

    private ApiGateway mApiGateway;
    private LocalRepository mLocalRepository;

    public FirstScreenBO(@NonNull ApiGateway apiGateway, @NonNull LocalRepository localRepository) {
        mApiGateway = apiGateway;
        mLocalRepository = localRepository;
    }

    public FirstScreenDTO getLocalData() {
        return mLocalRepository.getFirstScreenData();
    }

    public Disposable fetchRemote() {
        int width = InfoBus.getDeviceInfo().getScreenWidth();
        int height = InfoBus.getAppContext().getResources().getDimensionPixelSize(R.dimen.dimen_1584px);
        Logger.d(TAG, String.format(Locale.US, "fetch, width = %d, height = %d", width, height));
        return mApiGateway.getFirstScreen(width, height)
            .subscribe(new Consumer<FirstScreenDTO>() {
                @Override
                public void accept(FirstScreenDTO firstScreenDTO) throws Exception {
                    mLocalRepository.saveFirstScreenData(firstScreenDTO);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Logger.d(TAG, throwable.toString());
                }
            });
    }
}
