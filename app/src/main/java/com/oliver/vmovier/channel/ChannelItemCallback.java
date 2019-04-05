package com.oliver.vmovier.channel;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.text.TextUtils;

public class ChannelItemCallback extends ItemCallback<ChannelVO> {

    @Override
    public boolean areItemsTheSame(@NonNull ChannelVO channelVO, @NonNull ChannelVO t1) {
        return TextUtils.equals(channelVO.getName(), t1.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ChannelVO channelVO, @NonNull ChannelVO t1) {
        return TextUtils.equals(channelVO.getIconUrl(), t1.getIconUrl())
            && channelVO.getCateType() == t1.getCateType()
            && TextUtils.equals(channelVO.getCateName(), t1.getCateName())
            && TextUtils.equals(channelVO.getCateNav(), t1.getCateNav());
    }
}
