package com.oliver.vmovier.detail.article;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Info.InfoBus;
import com.squareup.picasso.Picasso;

public class ImageViewHolder extends BaseRVViewHolder {

    private AppCompatImageView mImageView;

    public ImageViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        ImageVO imageVO = (ImageVO)data;
        MarginLayoutParams layoutParams = (MarginLayoutParams)mImageView.getLayoutParams();
        int width = InfoBus.getDeviceInfo().getScreenWidth() - layoutParams.leftMargin - layoutParams.rightMargin;
        layoutParams.height = imageVO.getHeight() * width / imageVO.getWidth();
        Picasso.get().load(imageVO.getUrl()).into(mImageView);
    }

    @Override
    protected void setupView() {
        mImageView = itemView.findViewById(R.id.article_image);
    }
}
