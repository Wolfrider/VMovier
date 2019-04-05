package com.oliver.vmovier.view.player;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.view.player.PlayBottomBar.OnPlayBottomListener;
import com.oliver.vmovier.view.player.PlayHeaderBar.OnPlayHeaderListener;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class MediaPlayerProxy {

    public interface OnMediaPlayerListener {

        void onBackClick();

        void onFullScreenChange(boolean isFullScreen);
    }

    private static final String TAG = "MediaPlayerProxy";

    private Context mContext;
    private MediaPlayer mMediaPlayer;

    private RelativeLayout mContainer;
    private PlayHeaderBar mPlayHeaderBar;
    private PlayBottomBar mPlayBottomBar;
    private OnMediaPlayerListener mOnMediaPlayerListener;

    private boolean mHasDestroyed = false;
    private boolean mHasPlayed = false;
    private Disposable mTimer;
    private boolean mIsFullScreen = false;


    public MediaPlayerProxy(@NonNull Context context, @NonNull RelativeLayout container) {
        mContext = context;
        mMediaPlayer = new MediaPlayer();
        mContainer = container;
        init();
    }

    public void setOnMediaPlayerListener(OnMediaPlayerListener listener) {
        mOnMediaPlayerListener = listener;
    }

    public void play(@NonNull String url, int duration) {
        try {
            Logger.d(TAG, "play " + url);
            mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Logger.d(TAG, "onPrepared");
                    resume();
                }
            });
            mMediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    //Logger.d(TAG, "onBufferingUpdate percent = " + percent);

                }
            });
            mMediaPlayer.setDataSource(mContext, Uri.parse(url));
            mMediaPlayer.prepareAsync();
            mPlayBottomBar.setCurrentProgress(0);
            mPlayBottomBar.setMaxProgress(duration);
            mHasPlayed = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void release() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }

    public void resume() {
        if (!mMediaPlayer.isPlaying() && mHasPlayed) {
            mMediaPlayer.start();
            mPlayBottomBar.setIsPlaying(true);
            mTimer = Observable.interval(500, 500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    int position = mMediaPlayer.getCurrentPosition() / 1000;
                    Logger.d(TAG, "getCurrentPosition = " + position);
                    mPlayBottomBar.setCurrentProgress(position);
                }
            });
        }
    }

    public void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayBottomBar.setIsPlaying(false);
            if (null != mTimer) {
                mTimer.dispose();
            }
            mTimer = null;
        }
    }

    private void init() {
        initPlayer();
        initHeaderBar();
        initBottomBar();
    }

    private void initPlayer() {
        SurfaceView surfaceView = new SurfaceView(mContext);
        mContainer.addView(surfaceView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        surfaceView.getHolder().addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Logger.d(TAG, "surfaceCreated");
                mMediaPlayer.setDisplay(holder);
                if (mHasDestroyed) {
                    resume();
                    mHasDestroyed = false;
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Logger.d(TAG, "surfaceChanged");
                mMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Logger.d(TAG, "surfaceDestroyed");
                pause();
                mHasDestroyed = true;
            }
        });
    }

    private void initHeaderBar() {
        mPlayHeaderBar = new PlayHeaderBar(mContext, mContainer);
        mPlayHeaderBar.setOnPlayHeaderListener(new OnPlayHeaderListener() {
            @Override
            public void onBackClick() {
                if (null != mOnMediaPlayerListener) {
                    if (mIsFullScreen) {
                        mPlayBottomBar.switchFullscreen(false);
                    } else {
                        mOnMediaPlayerListener.onBackClick();
                    }
                }
            }
        });
    }

    private void initBottomBar() {
        mPlayBottomBar = new PlayBottomBar(mContext, mContainer);
        mPlayBottomBar.setOnPlayBottomListener(new OnPlayBottomListener() {
            @Override
            public void onStatusChange(boolean isPlaying) {
                if (isPlaying) {
                    mMediaPlayer.start();
                } else {
                    mMediaPlayer.pause();
                }
            }

            @Override
            public void onSeekChange(int progress) {
                mMediaPlayer.seekTo(progress * 1000);
            }

            @Override
            public void onFullScreenClick(boolean isFullScreen) {
                mIsFullScreen = isFullScreen;
                if (null != mOnMediaPlayerListener) {
                    mOnMediaPlayerListener.onFullScreenChange(isFullScreen);
                }
            }
        });
    }
}
