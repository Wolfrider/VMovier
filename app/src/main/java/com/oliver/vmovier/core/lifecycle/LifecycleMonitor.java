package com.oliver.vmovier.core.lifecycle;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.app.Application;
import android.support.annotation.NonNull;
import com.oliver.vmovier.core.utils.Logger;

public class LifecycleMonitor {

    private static final String TAG = "LifecycleMonitor";

    private static LifecycleCallbacks mLifecycleCallbacks;
    private static List<OnLifecycleListener> mLifecycleListeners;

    static {
        mLifecycleListeners = new LinkedList<>();
        mLifecycleCallbacks = new LifecycleCallbacks();
    }

    public static void init(@NonNull Application application) {
        application.registerActivityLifecycleCallbacks(mLifecycleCallbacks);
    }

    public static void register(@NonNull OnLifecycleListener lifecycleListener) {
        mLifecycleListeners.add(lifecycleListener);
    }

    public static void unregister(@NonNull OnLifecycleListener lifecycleListener) {
        mLifecycleListeners.remove(lifecycleListener);
    }

    static void forEach(LifecycleEvent event, @NonNull LifecycleState state) {
        Logger.v(TAG, String.format(Locale.US, "Activity %s, %s", event.toString(), state.mActivity));
        for (OnLifecycleListener lifecycleListener: mLifecycleListeners) {
            switch (event) {
                case ON_CREATE:
                    lifecycleListener.onCreate(state);
                    break;
                case ON_START:
                    lifecycleListener.onStart(state);
                    break;
                case ON_RESUME:
                    lifecycleListener.onResume(state);
                    break;
                case ON_PAUSE:
                    lifecycleListener.onPause(state);
                    break;
                case ON_STOP:
                    lifecycleListener.onStop(state);
                    break;
                case ON_DESTROY:
                    lifecycleListener.onDestroy(state);
                    break;
                default:
                    break;
            }
        }
    }
}
