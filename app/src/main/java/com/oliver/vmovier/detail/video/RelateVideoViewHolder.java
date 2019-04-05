package com.oliver.vmovier.detail.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;

public class RelateVideoViewHolder extends BaseRVViewHolder {

    private AppCompatTextView mTitleTextView;
    private View mMoreView;
    private ViewPager mViewPager;
    private RelateVideoAdapter mAdapter;

    public RelateVideoViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        final RelateVideoVO relateVO = (RelateVideoVO)data;
        mTitleTextView.setText(relateVO.getName());
        if (TextUtils.isEmpty(relateVO.getScheme())) {
            mMoreView.setVisibility(View.GONE);
        } else {
            mMoreView.setVisibility(View.VISIBLE);
            mMoreView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Router.Builder(mContext, NavType.SCHEME)
                        .setContent(relateVO.getScheme())
                        .build().nav();
                }
            });
        }
        mAdapter.refresh(relateVO.getRelateList());
    }

    @Override
    protected void setupView() {
        mTitleTextView = itemView.findViewById(R.id.video_relate_title);
        mMoreView = itemView.findViewById(R.id.video_relate_more);
        mViewPager = itemView.findViewById(R.id.video_relate_content);
        mAdapter = new RelateVideoAdapter(mContext);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
    }
}
