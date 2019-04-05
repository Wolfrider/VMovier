package com.oliver.vmovier.discovery;

import java.util.Locale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView.ScaleType;
import com.oliver.vmovier.R;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;
import com.oliver.vmovier.view.banner.AutoBannerViewHolder;
import com.squareup.picasso.Picasso;

public class AlbumBannerItemViewHolder extends AutoBannerViewHolder {

    private static final String TAG = "SimpleBannerItemViewHolder";

    private View mViewContainer;
    private AppCompatImageView mImageView;
    private AppCompatTextView mTitleTextView;
    private AppCompatTextView mSubTitleTextView;

    public AlbumBannerItemViewHolder(@NonNull Context context) {
        setupView(context);
    }

    @Override
    public View getView() {
        return mViewContainer;
    }

    @Override
    public void onBindData(@NonNull Object args) {
        final AlbumItemVO albumItemVO = (AlbumItemVO)args;
        Logger.d(TAG, "onBindData " + albumItemVO.getTitle());
        Picasso.get().load(albumItemVO.getImageUrl()).into(mImageView);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "onClick " + albumItemVO.getImageUrl());
                new Router.Builder(mImageView.getContext(), NavType.VIDEO)
                    .setContent(albumItemVO.getId())
                    .build().nav();
            }
        });
        mTitleTextView.setText(albumItemVO.getTitle());
        mSubTitleTextView.setText(albumItemVO.getSubTitle());
    }

    private void setupView(Context context) {
        mViewContainer = LayoutInflater.from(context).inflate(R.layout.discovery_album_layout, null);
        mImageView = mViewContainer.findViewById(R.id.discovery_album_image);
        mTitleTextView = mViewContainer.findViewById(R.id.discovery_album_title);
        mSubTitleTextView = mViewContainer.findViewById(R.id.discovery_album_subtitle);
    }
}