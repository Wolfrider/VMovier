package com.oliver.vmovier.detail.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnClickListener;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;

public class IntroViewHolder extends BaseRVViewHolder {

    private static final String TAG = "VideoIntroViewHolder";

    private AppCompatTextView mTitleTextView;
    private AppCompatTextView mSubTitleTextView;
    private AppCompatTextView mContentTextView;
    private View mReadMoreView;

    public IntroViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        final IntroVO videoIntroVO = (IntroVO)data;
        mTitleTextView.setText(videoIntroVO.getTitle());
        mSubTitleTextView.setText(videoIntroVO.getSubTitle());
        mContentTextView.setText(videoIntroVO.getContent());
        mReadMoreView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "read more");
                new Router.Builder(mContext, NavType.ARTICLE)
                    .setBundle(videoIntroVO.getArticleVO().toBundle())
                    .build().nav();
            }
        });
    }

    @Override
    protected void setupView() {
        mTitleTextView = itemView.findViewById(R.id.video_intro_title);
        mSubTitleTextView = itemView.findViewById(R.id.video_intro_subtitle);
        mContentTextView = itemView.findViewById(R.id.video_intro_content);
        mReadMoreView = itemView.findViewById(R.id.video_intro_more);
    }
}
