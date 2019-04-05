package com.oliver.vmovier.detail.video;

import java.util.List;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.core.Constant.CardType.VideoDetail;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.view.player.MediaPlayerProxy;
import com.oliver.vmovier.view.player.MediaPlayerProxy.OnMediaPlayerListener;

public class VideoDetailView extends BaseView {

    private static final String TAG = "VideoDetailView";

    private VideoDetailViewModel mViewModel;

    private MediaPlayerProxy mMediaPlayerProxy;
    private RelativeLayout mPlayerView;
    private RecyclerView mRecyclerView;
    private View mBottomBar;
    private AppCompatTextView mLikeTextView;
    private AppCompatTextView mShareTextView;
    private BaseRVAdapter mAdapter;
    private boolean mIsLoading;

    public VideoDetailView(@NonNull AppCompatActivity activity, @NonNull String id) {
        super(activity);
        mViewModel = ViewModelProviders.of(mActivity).get(VideoDetailViewModel.class);
        mViewModel.init(id);
    }

    @Override
    public void init() {
        Logger.d(TAG, "init");
        setupView();
        setupData();
        setupMediaPlayer();
    }

    public void onResume() {
        //mMediaPlayerProxy.resume();
    }

    public void onPause() {
        //mMediaPlayerProxy.pause();
    }

    public void release() {
        mMediaPlayerProxy.release();
    }

    private void setupView() {
        mBottomBar = mActivity.findViewById(R.id.video_bottom_bar);
        mLikeTextView = mActivity.findViewById(R.id.video_like_count);
        mShareTextView = mActivity.findViewById(R.id.video_share_count);
        setupRecyclerView();
    }

    private void setupData() {
        mViewModel.getProfile().observe(mActivity, new Observer<ProfileVO>() {
            @Override
            public void onChanged(@Nullable ProfileVO videoProfileVO) {
                if (null != videoProfileVO) {
                    playVideo(videoProfileVO);
                }
            }
        });
        mViewModel.getInteractive().observe(mActivity, new Observer<InteractiveVO>() {
            @Override
            public void onChanged(@Nullable InteractiveVO videoInteractiveVO) {
                if (null != videoInteractiveVO) {
                    mLikeTextView.setText(String.valueOf(videoInteractiveVO.getLikeCount()));
                    mShareTextView.setText(String.valueOf(videoInteractiveVO.getShareCount()));
                }
            }
        });
        mViewModel.getContent().observe(mActivity, new Observer<List<IRVItemVO>>() {
            @Override
            public void onChanged(@Nullable List<IRVItemVO> baseVideoDetailVOS) {
                if (null != baseVideoDetailVOS) {
                    if (mIsLoading) {
                        mAdapter.append(baseVideoDetailVOS);
                        mIsLoading = false;
                    } else {
                        mAdapter.refresh(baseVideoDetailVOS);
                    }
                }
            }
        });
    }

    private void setupMediaPlayer() {
        mPlayerView = mActivity.findViewById(R.id.video_player_container);
        mMediaPlayerProxy = new MediaPlayerProxy(mActivity, mPlayerView);
        mMediaPlayerProxy.setOnMediaPlayerListener(new OnMediaPlayerListener() {

            private ViewGroup.LayoutParams mPlayerSmallLayoutParams;

            @Override
            public void onBackClick() {
                mActivity.finish();
            }

            @Override
            public void onFullScreenChange(boolean isFullScreen) {
                if (isFullScreen) {
                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    mRecyclerView.setVisibility(View.GONE);
                    mBottomBar.setVisibility(View.GONE);
                    mPlayerSmallLayoutParams = mPlayerView.getLayoutParams();
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(mPlayerSmallLayoutParams);
                    layoutParams.width = layoutParams.height = LayoutParams.MATCH_PARENT;
                    mPlayerView.setLayoutParams(layoutParams);
                } else {
                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mBottomBar.setVisibility(View.VISIBLE);
                    mPlayerView.setLayoutParams(mPlayerSmallLayoutParams);
                }
            }
        });
    }

    private void setupRecyclerView() {
        mRecyclerView = mActivity.findViewById(R.id.video_content);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (layoutManager.findLastVisibleItemPosition() > layoutManager.getItemCount() - 3) {
                    if (!mIsLoading) {
                        mViewModel.loadMore();
                        mIsLoading = true;
                    }
                }
            }
        });
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(mActivity.getDrawable(R.drawable.video_detail_divider));
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new BaseRVAdapter(mActivity, createFactory());
        mRecyclerView.setAdapter(mAdapter);
    }

    private IRVViewHolderFactory createFactory() {
        final LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        return new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                switch (viewType) {
                    case VideoDetail.INTRO:
                        return new IntroViewHolder(context,
                            layoutInflater.inflate(R.layout.video_intro_card_item, viewGroup, false));
                    case VideoDetail.RELATED:
                        return new RelateVideoViewHolder(context,
                            layoutInflater.inflate(R.layout.video_relate_card_item, viewGroup, false));
                    case VideoDetail.COMMENT:
                        return new CommentsViewHolder(context,
                            layoutInflater.inflate(R.layout.video_comment_card_item, viewGroup, false));
                    default:
                        break;
                }
                throw new IllegalArgumentException();
            }
        };
    }

    private void playVideo(ProfileVO profileVO) {
        mMediaPlayerProxy.play(profileVO.getDefaultUrl(), profileVO.getDuration());
    }
}
