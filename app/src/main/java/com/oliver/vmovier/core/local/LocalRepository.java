package com.oliver.vmovier.core.local;

import com.alibaba.fastjson.JSON;

import android.text.TextUtils;
import com.oliver.vmovier.core.Constant.Local;
import com.oliver.vmovier.core.Info.InfoBus;
import com.oliver.vmovier.core.dto.FirstScreenDTO;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.VFiles;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class LocalRepository {

    private static final String TAG = "LocalRepository";

    private String mDirPath;
    private String mFirstScreenJsonPath;
    private FirstScreenDTO mFirstScreenData;

    public LocalRepository() {
        mDirPath = VFiles.combine(VFiles.getAppFileDir(InfoBus.getAppContext()), Local.DIR_NAME);
        mFirstScreenJsonPath = VFiles.combine(mDirPath, Local.FIRST_SCREEN_JSON_NAME);
    }

    public Completable loadLocal() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                loadLocalImpl();
            }
        }).subscribeOn(Schedulers.io());
    }

    public void saveFirstScreenData(FirstScreenDTO dto) {
        if (null != dto && !TextUtils.isEmpty(dto.getImageUrl())) {
            String content = JSON.toJSONString(dto);
            VFiles.writeContent(mFirstScreenJsonPath, content);
            Logger.d(TAG, "saveFirstScreenData, " + content);
        }
    }

    public FirstScreenDTO getFirstScreenData() {
        return mFirstScreenData;
    }

    private void loadLocalImpl() {
        VFiles.mkdirs(mDirPath);
        Logger.d(TAG, "loadLocalImpl path = " + mFirstScreenJsonPath);
        if (VFiles.exists(mFirstScreenJsonPath)) {
            mFirstScreenData = JSON.parseObject(VFiles.readFile(mFirstScreenJsonPath), FirstScreenDTO.class);
        } else {
            Logger.d(TAG, "loadLocalImpl json not find");
        }
    }
}
