package com.oliver.vmovier.discovery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Router;

public class TitleViewHolder extends BaseRVViewHolder {

    private AppCompatTextView mTitleTextView;
    private AppCompatTextView mMoreTipsTextView;

    public TitleViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        final TitleItemVO titleItemVO = (TitleItemVO)data;
        mTitleTextView.setText(titleItemVO.getTitle());
        if (TextUtils.isEmpty(titleItemVO.getScheme())) {
            mMoreTipsTextView.setVisibility(View.GONE);
        } else {
            mMoreTipsTextView.setVisibility(View.VISIBLE);
            mMoreTipsTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Router.Builder(mContext, NavType.SCHEME)
                        .setContent(titleItemVO.getScheme())
                        .build().nav();
                }
            });
        }
    }

    @Override
    protected void setupView() {
        mTitleTextView = itemView.findViewById(R.id.discovery_title);
        mMoreTipsTextView = itemView.findViewById(R.id.discovery_title_more);
    }
}
