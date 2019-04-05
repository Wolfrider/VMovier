package com.oliver.vmovier.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.oliver.vmovier.core.utils.Logger;

public class RefreshLayout extends SwipeRefreshLayout {

    private static final String TAG = "RefreshLayout";

    private int mTouchSlop;
    private float mStartX;
    private float mStartY;
    private boolean mEnableChildHandleTouch;

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isEnabled()) {
            int action = ev.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mStartX = ev.getX();
                    mStartY = ev.getY();
                    mEnableChildHandleTouch = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mEnableChildHandleTouch) {
                        return false;
                    }
                    if (Math.abs(ev.getX() - mStartX) > mTouchSlop) {
                        if (Math.abs(ev.getX() - mStartX) > Math.abs(ev.getY() - mStartY)) {
                            mEnableChildHandleTouch = true;
                            Logger.d(TAG, "mEnableChildHandleTouch true");
                            return false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
