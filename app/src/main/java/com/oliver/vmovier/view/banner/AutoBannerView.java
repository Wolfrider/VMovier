package com.oliver.vmovier.view.banner;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.oliver.vmovier.R;
import com.oliver.vmovier.core.utils.Logger;

public class AutoBannerView extends RelativeLayout {

    private static final String TAG = "AutoBannerView";

    public interface OnBannerChangeListener {

        void onSelected(int position);
    }

    private Handler mHandler;
    private boolean mEnableAutoScroll;
    private int mIntervalMills;
    private int mIndicatorPaddingBottom;
    private int mIndicatorSpacing;
    private Drawable mIndicatorSelected;
    private Drawable mIndicatorUnSelected;
    private int mLastPosition = -1;

    private boolean mScrolling;
    private AutoBannerAdapter mAdapter;
    private ViewPager mViewPager;
    private LinearLayout mIndicator;

    private DataSetObserver mObserver;
    private List<OnBannerChangeListener> mOnBannerChangeListeners;

    public AutoBannerView(@NonNull Context context) {
        super(context);
    }

    public AutoBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler(Looper.getMainLooper());
        initAttrs(attrs);
        initViewPager();
        initIndicator();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (canStart()) {
            start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        //switch (action) {
        //    case MotionEvent.ACTION_MOVE:
        //        if (mScrolling) {
        //            Logger.d(TAG, "Scrolling, requestDisallowInterceptTouchEvent(true)");
        //            getParent().requestDisallowInterceptTouchEvent(true);
        //        } else {
        //            Logger.d(TAG, "Not Scrolling, requestDisallowInterceptTouchEvent(false)");
        //            getParent().requestDisallowInterceptTouchEvent(false);
        //        }
        //        break;
        //    case MotionEvent.ACTION_CANCEL:
        //    case MotionEvent.ACTION_UP:
        //        getParent().requestDisallowInterceptTouchEvent(false);
        //        break;
        //    default:
        //        break;
        //}
        return super.dispatchTouchEvent(ev);
    }

    public void setAutoScroll(boolean enable) {
        mEnableAutoScroll = enable;
    }

    public boolean isAutoScroll() {
        return mEnableAutoScroll;
    }

    public void setInterval(int intervalMills) {
        if (intervalMills > 0) {
            mIntervalMills = intervalMills;
        }
    }

    public int getInterval() {
        return mIntervalMills;
    }

    public void start() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onTimer();
            }
        }, mIntervalMills);
    }

    public void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public void setAdapter(@NonNull AutoBannerAdapter adapter) {
        if (null != mAdapter && null != mObserver) {
            mAdapter.unregisterDataSetObserver(mObserver);
        }
        mAdapter = adapter;
        mViewPager.setAdapter(adapter);
        if (null == mObserver) {
            mObserver = new DataSetObserver() {
                @Override
                public void onChanged() {
                    onDataChanged();
                }

                @Override
                public void onInvalidated() {
                    onDataChanged();
                }
            };
        }
        mAdapter.registerDataSetObserver(mObserver);
    }

    public void setCurrentPosition(int position) {
        Logger.d(TAG, "setCurrentPosition position = " + position);
        mViewPager.setCurrentItem(position);
    }

    public void getCurrentPosition() {
        mAdapter.getRealPosition(mViewPager.getCurrentItem());
    }

    public void addOnBannerChangeListener(@NonNull OnBannerChangeListener listener) {
        mOnBannerChangeListeners.add(listener);
    }

    public void removeOnBannerChangeListener(@NonNull OnBannerChangeListener listener) {
        mOnBannerChangeListeners.remove(listener);
    }

    private void initAttrs(@Nullable AttributeSet attrs) {
        if (null != attrs) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AutoBannerView);
            if (null != typedArray) {
                mEnableAutoScroll = typedArray.getBoolean(R.styleable.AutoBannerView_autoScroll, true);
                mIntervalMills = typedArray.getInt(R.styleable.AutoBannerView_interval, 0);
                mIndicatorPaddingBottom = typedArray.getDimensionPixelSize(R.styleable.AutoBannerView_indicatorPaddingBottom, 0);
                mIndicatorSpacing = typedArray.getDimensionPixelSize(R.styleable.AutoBannerView_indicatorSpacing, 0);
                mIndicatorSelected = typedArray.getDrawable(R.styleable.AutoBannerView_indicatorSelected);
                mIndicatorUnSelected = typedArray.getDrawable(R.styleable.AutoBannerView_indicatorUnSelected);
                typedArray.recycle();
            }
        } else {
            initDefaultAttrs();
        }
    }

    private void initDefaultAttrs() {
        mEnableAutoScroll = true;
        mIntervalMills = 0;
        mIndicatorPaddingBottom = 0;
        mIndicatorSpacing = 0;
    }

    private void initViewPager() {
        mViewPager = new ViewPager(getContext());
        addView(mViewPager, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mOnBannerChangeListeners = new ArrayList<>(16);
        mViewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int i) {
                int position = mAdapter.getRealPosition(i);
                Logger.d(TAG, "onSelected position = " + position);
                for (OnBannerChangeListener listener: mOnBannerChangeListeners) {
                    listener.onSelected(position);
                }
                onSelected(position);
                mLastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Logger.d(TAG, "onPageScrollStateChanged state = " + i);
                if (i == ViewPager.SCROLL_STATE_IDLE) {
                    mScrolling = false;
                    if (canStart()) {
                        start();
                    }
                } else {
                    mScrolling = true;
                    stop();
                }

            }
        });
    }

    private void initIndicator() {
        mIndicator = new LinearLayout(getContext());
        mIndicator.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, mIndicatorPaddingBottom);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(CENTER_HORIZONTAL);
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        addView(mIndicator, layoutParams);
    }


    private void onTimer() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        Logger.d(TAG, "onTimer " + mViewPager.getCurrentItem());
    }

    private boolean canStart() {
        return mEnableAutoScroll && mIntervalMills > 0 && !mScrolling;
    }

    private void onDataChanged() {
        mIndicator.removeAllViews();
        int count = mAdapter.getRealCount();
        for (int i = 0; i < count; ++i) {
            View view = new View(getContext());
            Logger.d(TAG, String.format("indicator width = %d, height = %d", mIndicatorUnSelected.getIntrinsicWidth(), mIndicatorUnSelected.getIntrinsicHeight()));
            view.setBackground(mIndicatorUnSelected);
            MarginLayoutParams layoutParams = new MarginLayoutParams(mIndicatorUnSelected.getIntrinsicWidth(), mIndicatorUnSelected.getIntrinsicHeight());
            if (0 != i) {
                layoutParams.setMargins(mIndicatorSpacing, 0, 0, 0);
            }
            mIndicator.addView(view, layoutParams);
        }
    }

    private void onSelected(int position) {
        mIndicator.getChildAt(position).setBackground(mIndicatorSelected);
        if (mLastPosition >= 0) {
            mIndicator.getChildAt(mLastPosition).setBackground(mIndicatorUnSelected);
        }
    }

}
