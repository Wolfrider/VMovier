package com.oliver.vmovier.core.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnClickListener;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Router;
import com.squareup.picasso.Picasso;

public class PostViewHolder extends BaseRVViewHolder {

    private AppCompatImageView mImageView;
    private AppCompatTextView mTitleView;
    private AppCompatTextView mSubTitleView;

    public PostViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        final PostVO postVO = (PostVO)data;
        Picasso.get().load(postVO.getImageUrl()).into(mImageView);
        mTitleView.setText(postVO.getTitle());
        mSubTitleView.setText(postVO.getSubTitle());
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Router.Builder(mImageView.getContext(), NavType.VIDEO)
                    .setContent(postVO.getId())
                    .build().nav();
            }
        });
    }

    @Override
    protected void setupView() {
        mImageView = itemView.findViewById(R.id.post_list_image);
        mTitleView = itemView.findViewById(R.id.post_list_title);
        mSubTitleView = itemView.findViewById(R.id.post_list_subtitle);
    }


}
