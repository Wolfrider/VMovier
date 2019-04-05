package com.oliver.vmovier.core.download.inner.status;

import android.util.ArrayMap;
import android.util.SparseArray;

public class StatusContext {

    public static final int NONE = 0x01;

    public static final int PENDING = 0x02;

    public static final int PAUSED = 0x03;

    public static final int CANCELED = 0x04;

    public static final int FINISHED = 0x05;

    private ArrayMap<Integer, BaseStatus> mStatus;
    private BaseStatus mPrev;
    private BaseStatus mCurrent;

    public StatusContext() {
        mStatus = new ArrayMap<>();
        mStatus.put(NONE, new NoneStatus());
    }

    public void move(int status) {
        mPrev = mCurrent;

    }


}
