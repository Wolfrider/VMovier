package com.oliver.vmovier.home.daily;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.core.utils.Logger;
import com.squareup.picasso.Picasso;

public class DailyCoverDetailView extends BaseView {

    private static final String TAG = "DailyCoverDetailView";

    private DailyCoverVO mDailyCoverVO;
    private AppCompatImageView mCloseButton;

    private CardView mContentView;

    public DailyCoverDetailView(@NonNull AppCompatActivity activity, @NonNull DailyCoverVO dailyCoverVO) {
        super(activity);
        mDailyCoverVO = dailyCoverVO;
    }

    @Override
    public void init() {
        Logger.trace(TAG);
        setupView();
    }

    public void release() {
        Logger.d(TAG, "release");
        mContentView.clearAnimation();
    }

    private void setupView() {
        AppCompatImageView backgroundImageView = mActivity.findViewById(R.id.daily_cover_background);
        if (null == mDailyCoverVO) {
            return;
        }
        Picasso.get().load(mDailyCoverVO.getBackgroundImageUrl()).into(backgroundImageView);
        mCloseButton = mActivity.findViewById(R.id.daily_cover_close);
        mCloseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "exist Daily Cover.");
                mActivity.finish();
            }
        });
        fillContent();
        playAnimation();
    }

    private void fillContent() {
        AppCompatImageView imageView = mActivity.findViewById(R.id.daily_cover_image);
        Picasso.get().load(mDailyCoverVO.getImageUrl()).into(imageView);
        AppCompatTextView dayTextView = mActivity.findViewById(R.id.daily_cover_day);
        dayTextView.setText(mDailyCoverVO.getDay());
        AppCompatTextView monthTextView = mActivity.findViewById(R.id.daily_cover_month);
        monthTextView.setText(mDailyCoverVO.getMonth());
        AppCompatTextView weekTextView = mActivity.findViewById(R.id.daily_cover_week);
        weekTextView.setText(mDailyCoverVO.getWeek());
        AppCompatTextView titleTextView = mActivity.findViewById(R.id.daily_cover_title);
        titleTextView.setText(mDailyCoverVO.getTitle());
        AppCompatTextView contentTextView = mActivity.findViewById(R.id.daily_cover_content);
        contentTextView.setText(mDailyCoverVO.getContent());
    }

    private void playAnimation() {
        mContentView = mActivity.findViewById(R.id.daily_cover_content_container);
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
            Animation.RELATIVE_TO_SELF, -1.0F , Animation.RELATIVE_TO_SELF, 0);
        animation.setDuration(2000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mCloseButton.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCloseButton.setVisibility(View.VISIBLE);
                mContentView.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mContentView.startAnimation(animation);
    }
}
