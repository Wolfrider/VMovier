package com.oliver.vmovier.detail.article;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.utils.Logger;

public class HeaderViewHolder extends BaseRVViewHolder {

    private AppCompatTextView mTextView;

    public HeaderViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        HeaderVO headerVO = (HeaderVO)data;
        mTextView.setText(headerVO.getTitle());
    }

    @Override
    protected void setupView() {
        mTextView = itemView.findViewById(R.id.article_text);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setTypeface(Typeface.DEFAULT_BOLD);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.dimen_54px));
        mTextView.setGravity(Gravity.CENTER);
    }
}
