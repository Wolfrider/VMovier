package com.oliver.vmovier.core.post;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.text.TextUtils;

public class PostItemCallback extends ItemCallback<PostVO> {

    @Override
    public boolean areItemsTheSame(@NonNull PostVO postVO, @NonNull PostVO t1) {
        return TextUtils.equals(postVO.getId(), t1.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull PostVO postVO, @NonNull PostVO t1) {
        return TextUtils.equals(postVO.getTitle(), t1.getTitle())
            && TextUtils.equals(postVO.getSubTitle(), t1.getSubTitle())
            && TextUtils.equals(postVO.getImageUrl(), t1.getImageUrl());
    }
}
