package com.oliver.vmovier.home.daily;

import java.util.Locale;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.widget.PopupWindow;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;

public class DailyCoverView extends BaseView {

    private static final String TAG = "DailyCoverView";

    private DailyCoverViewModel mViewModel;

    private View mDailyCoverViewContainer;
    private AppCompatImageView mUnReadImageView;

    public DailyCoverView(@NonNull AppCompatActivity activity) {
        super(activity);
        mActivity = activity;
        mViewModel = ViewModelProviders.of(mActivity).get(DailyCoverViewModel.class);
    }

    @Override
    public void init() {
        setupView();
    }

    public void onShow() {
        Logger.trace(TAG);
        DailyCoverVO dailyCoverVO = mViewModel.getData().getValue();
        final Boolean isUnRead = mViewModel.isUnRead().getValue();
        if (canShowPopupTips(dailyCoverVO, isUnRead)) {
            showPopupTips(dailyCoverVO);
        } else {
            mViewModel.getData().observe(mActivity, new Observer<DailyCoverVO>() {
                @Override
                public void onChanged(@Nullable DailyCoverVO dailyCoverVO) {
                    if (canShowPopupTips(dailyCoverVO, isUnRead)) {
                        showPopupTips(dailyCoverVO);
                    }
                }
            });
        }
    }

    private void setupView() {
        mDailyCoverViewContainer = mActivity.findViewById(R.id.home_daily_cover_container);
        mDailyCoverViewContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mViewModel.getData().getValue()) {
                    showCover();
                    mViewModel.read();
                    mUnReadImageView.setVisibility(View.GONE);
                }
            }
        });
        AppCompatTextView dateTextView = mActivity.findViewById(R.id.home_daily_cover_date);
        dateTextView.setText(String.format(Locale.US, "%02d", mViewModel.getToday()));
        mUnReadImageView = mActivity.findViewById(R.id.home_daily_cover_unread);
        mViewModel.isUnRead().observe(mActivity, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (null != aBoolean) {
                    Logger.d(TAG, "isUnRead " + aBoolean);
                    mUnReadImageView.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

    private boolean canShowPopupTips(@Nullable DailyCoverVO dailyCoverVO, @Nullable Boolean isUnRead) {
        return null != dailyCoverVO && null != isUnRead && isUnRead;
    }

    private void showPopupTips(@NonNull DailyCoverVO dailyCoverVO) {
        Logger.d(TAG, "showPopupTips " + dailyCoverVO);
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.daily_cover_tips, null);
        AppCompatTextView timeTextView = contentView.findViewById(R.id.daily_tips_time);
        timeTextView.setText(String.format(Locale.US, "%s.%s", dailyCoverVO.getMonth(), dailyCoverVO.getDay()));
        AppCompatTextView titleTextView = contentView.findViewById(R.id.daily_tips_title);
        titleTextView.setText(dailyCoverVO.getTitle());
        final PopupWindow popupWindow = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        int offsetY = mActivity.getResources().getDimensionPixelSize(R.dimen.dimen_24px);
        popupWindow.showAsDropDown(mDailyCoverViewContainer, 0, offsetY);
        playPopupAnimation(popupWindow, contentView);
    }

    private void playPopupAnimation(final PopupWindow popupWindow, View contentView) {
        Logger.trace(TAG);
        contentView.setAlpha(0);
        ValueAnimator enterAnimator = ObjectAnimator.ofFloat(contentView, "alpha", 0, 1.0F);
        ValueAnimator leftAnimator = ObjectAnimator.ofFloat(contentView, "alpha", 1.0F, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(leftAnimator).after(enterAnimator).after(3000);
        animatorSet.setDuration(4000);
        animatorSet.start();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                popupWindow.dismiss();
                Logger.d(TAG, "dismiss popupWindow");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void showCover(){
        Logger.trace(TAG);
        new Router.Builder(mActivity, NavType.DAILY_COVER)
            .setParcelableContent(mViewModel.getData().getValue()).build().nav();
    }
}
