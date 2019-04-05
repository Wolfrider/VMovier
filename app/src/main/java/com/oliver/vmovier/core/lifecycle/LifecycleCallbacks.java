package com.oliver.vmovier.core.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class LifecycleCallbacks implements ActivityLifecycleCallbacks {

    private int mActivityRefs;

    LifecycleCallbacks() {
        mActivityRefs = 0;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LifecycleState state = new LifecycleState();
        state.mActivity = activity;
        state.mIsInBackground = mActivityRefs <= 0 && !activity.isChangingConfigurations();
        LifecycleMonitor.forEach(LifecycleEvent.ON_CREATE, state);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++mActivityRefs;
        LifecycleState state = new LifecycleState();
        state.mActivity = activity;
        state.mIsInBackground = false;
        LifecycleMonitor.forEach(LifecycleEvent.ON_START, state);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LifecycleState state = new LifecycleState();
        state.mActivity = activity;
        state.mIsInBackground = false;
        LifecycleMonitor.forEach(LifecycleEvent.ON_RESUME, state);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LifecycleState state = new LifecycleState();
        state.mActivity = activity;
        state.mIsInBackground = false;
        LifecycleMonitor.forEach(LifecycleEvent.ON_PAUSE, state);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        --mActivityRefs;
        LifecycleState state = new LifecycleState();
        state.mActivity = activity;
        state.mIsInBackground = mActivityRefs <= 0 && !activity.isChangingConfigurations();
        LifecycleMonitor.forEach(LifecycleEvent.ON_STOP, state);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LifecycleState state = new LifecycleState();
        state.mActivity = activity;
        state.mIsInBackground = mActivityRefs <= 0 && !activity.isChangingConfigurations();;
        LifecycleMonitor.forEach(LifecycleEvent.ON_DESTROY, state);
    }
}
