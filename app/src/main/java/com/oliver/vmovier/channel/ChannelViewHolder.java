package com.oliver.vmovier.channel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnClickListener;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CateType;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Router;
import com.squareup.picasso.Picasso;

public class ChannelViewHolder extends BaseRVViewHolder {

    private AppCompatImageView mLogoImageView;
    private AppCompatTextView mNameTextView;

    public ChannelViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        final ChannelVO channelVO = (ChannelVO)data;
        Picasso.get().load(channelVO.getIconUrl()).into(mLogoImageView);
        mNameTextView.setText(channelVO.getName());
        mLogoImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (channelVO.getCateType() == CateType.LINK) {
                    new Router.Builder(mNameTextView.getContext(), NavType.WEBVIEW)
                        .setContent(channelVO.getCateNav())
                        .build().nav();
                } else {
                    new Router.Builder(mNameTextView.getContext(), NavType.CHANNEL_LIST)
                        .setParcelableContent(channelVO)
                        .build().nav();
                }
            }
        });
    }

    @Override
    protected void setupView() {
        mLogoImageView = itemView.findViewById(R.id.channel_card_image);
        mNameTextView = itemView.findViewById(R.id.channel_card_name);
    }
}
