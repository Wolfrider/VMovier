package com.oliver.vmovier.search;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import com.google.android.flexbox.FlexboxLayoutManager.LayoutParams;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;

public class RecommendViewHolder extends BaseRVViewHolder {

    public interface OnClickListener {

        void onClick(String tag);
    }

    private AppCompatTextView mTextView;
    private OnClickListener mOnClickListener;

    public RecommendViewHolder(@NonNull Context context, @NonNull View itemView, @NonNull OnClickListener listener) {
        super(context, itemView);
        mOnClickListener = listener;
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        final RecommendItemVO itemVO = (RecommendItemVO)data;
        mTextView.setText(itemVO.getWord());
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick(itemVO.getWord());
            }
        });
    }

    @Override
    protected void setupView() {
        mTextView = (AppCompatTextView)itemView;
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int margin = mContext.getResources().getDimensionPixelSize(R.dimen.dimen_12px);
        layoutParams.setMargins(margin, margin, margin, margin);
        mTextView.setLayoutParams(layoutParams);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.dimen_42px));
        mTextView.setTextColor(Color.WHITE);
        int paddingTop = mContext.getResources().getDimensionPixelSize(R.dimen.dimen_12px);
        int paddingLeft = mContext.getResources().getDimensionPixelOffset(R.dimen.dimen_18px);
        mTextView.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
        mTextView.setBackground(mContext.getDrawable(R.drawable.recommend_tag));
    }
}
