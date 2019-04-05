package com.oliver.vmovier.view.player;

import java.util.Locale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.oliver.vmovier.R;

public class PlayBottomBar {

    public interface OnPlayBottomListener {

        void onStatusChange(boolean isPlaying);

        void onSeekChange(int progress);

        void onFullScreenClick(boolean isFullScreen);
    }

    private Context mContext;
    private RelativeLayout mContainer;

    private View mWholeView;
    private AppCompatImageView mPlayButton;
    private AppCompatTextView mCurrentProgressTextView;
    private AppCompatTextView mMaxProgressTextView;
    private SeekBar mSeekBar;
    private AppCompatImageView mFullscreenButton;

    private int mCurrentProgress;
    private boolean mIsPlaying;

    private OnPlayBottomListener mOnPlayListener;

    public PlayBottomBar(@NonNull Context context, @NonNull RelativeLayout container) {
        mContext = context;
        mContainer = container;
        init();
    }

    public View getView() {
        return mWholeView;
    }

    public void setOnPlayBottomListener(@NonNull OnPlayBottomListener listener) {
        mOnPlayListener = listener;
    }

    public void setIsPlaying(boolean isPlaying) {
        mIsPlaying = isPlaying;
        if (mIsPlaying) {
            mPlayButton.setImageResource(R.drawable.play_button_topause);
        } else {
            mPlayButton.setImageResource(R.drawable.play_button_toplay);
        }
        if (null != mOnPlayListener) {
            mOnPlayListener.onStatusChange(mIsPlaying);
        }
    }

    public void setCurrentProgress(int progress) {
        if (mCurrentProgress != progress) {
            mCurrentProgress = progress;
            mCurrentProgressTextView.setText(formatProgress(mCurrentProgress));
            mSeekBar.setProgress(mCurrentProgress);
        }
    }

    public void setMaxProgress(int maxProgress) {
        mSeekBar.setMax(maxProgress);
        mMaxProgressTextView.setText(formatProgress(maxProgress));
    }

    public void switchFullscreen(boolean isFullscreen) {
        mFullscreenButton.setVisibility(isFullscreen ? View.GONE : View.VISIBLE);
        if (null != mOnPlayListener) {
            mOnPlayListener.onFullScreenClick(isFullscreen);
        }
    }

    private void init() {
        mWholeView = LayoutInflater.from(mContext).inflate(R.layout.play_bottom_bar, mContainer, false);
        mPlayButton = mWholeView.findViewById(R.id.play_bottom_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIsPlaying(!mIsPlaying);
            }
        });
        mCurrentProgressTextView = mWholeView.findViewById(R.id.play_bottom_current_progress);
        mSeekBar = mWholeView.findViewById(R.id.play_bottom_seekbar);
        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (null != mOnPlayListener) {
                        mOnPlayListener.onSeekChange(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBar.setPadding(0, mContext.getResources().getDimensionPixelSize(R.dimen.dimen_30px),
            0, mContext.getResources().getDimensionPixelSize(R.dimen.dimen_30px));
        mMaxProgressTextView = mWholeView.findViewById(R.id.play_bottom_max_progress);
        mFullscreenButton = mWholeView.findViewById(R.id.play_bottom_fullscreen);
        mFullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFullscreen(true);
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
            mContext.getResources().getDimensionPixelOffset(R.dimen.dimen_72px));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.bottomMargin = mContext.getResources().getDimensionPixelOffset(R.dimen.dimen_18px);
        mContainer.addView(mWholeView, layoutParams);
    }

    private String formatProgress(int progress) {
        return String.format(Locale.US, "%02d:%02d", progress/60, progress%60);
    }

    private void switchPlayButton() {
        RotateAnimation animation = new RotateAnimation(0, 360,
            Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFillAfter(true);
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!mIsPlaying) {
                    mPlayButton.setImageResource(R.drawable.play_button_topause);
                } else {
                    mPlayButton.setImageResource(R.drawable.play_button_toplay);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mPlayButton.setAnimation(animation);
        animation.startNow();
    }

}
