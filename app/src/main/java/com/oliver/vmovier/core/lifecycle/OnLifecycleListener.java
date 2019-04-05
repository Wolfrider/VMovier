package com.oliver.vmovier.core.lifecycle;

public interface OnLifecycleListener {

    void onCreate(LifecycleState state);

    void onStart(LifecycleState state);

    void onResume(LifecycleState state);

    void onPause(LifecycleState state);

    void onStop(LifecycleState state);

    void onDestroy(LifecycleState state);

}
