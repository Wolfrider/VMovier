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

public class FilterTagViewHolder extends FilterViewHolder {

    public FilterTagViewHolder(@NonNull Context context, @NonNull View itemView, @NonNull OnClickListener listener) {
        super(context, itemView, listener);
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
