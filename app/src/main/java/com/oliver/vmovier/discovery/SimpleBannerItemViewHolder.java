package com.oliver.vmovier.discovery;

import java.util.Locale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView.ScaleType;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;
import com.oliver.vmovier.view.banner.AutoBannerViewHolder;
import com.squareup.picasso.Picasso;

public class SimpleBannerItemViewHolder extends AutoBannerViewHolder {

    private static final String TAG = "SimpleBannerItemViewHolder";

    private AppCompatImageView mImageView;

    public SimpleBannerItemViewHolder(@NonNull Context context) {
        mImageView = new AppCompatImageView(context);
    }

    @Override
    public View getView() {
        return mImageView;
    }

    @Override
    public void onBindData(@NonNull Object args) {
        final BannerItemVO bannerItemVO = (BannerItemVO)args;
        Logger.d(TAG, "onBindData " + bannerItemVO.getImageUrl());
        mImageView.setScaleType(ScaleType.FIT_XY);
        Picasso.get().load(bannerItemVO.getImageUrl()).into(mImageView);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, String.format(Locale.US, "onClick %s, %s", bannerItemVO.getImageUrl(), bannerItemVO.getParam()));
                if (bannerItemVO.getParam().startsWith("http")) {
                    new Router.Builder(mImageView.getContext(), NavType.WEBVIEW)
                        .setContent(bannerItemVO.getParam())
                        .build().nav();
                } else {
                    new Router.Builder(mImageView.getContext(), NavType.VIDEO)
                        .setContent(bannerItemVO.getParam())
                        .build().nav();
                }
            }
        });
    }
}
