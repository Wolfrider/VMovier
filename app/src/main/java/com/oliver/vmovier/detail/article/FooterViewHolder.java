package com.oliver.vmovier.detail.article;

import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;

public class FooterViewHolder extends BaseRVViewHolder {

    private AppCompatTextView mTextView;

    public FooterViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        FooterVO footerVO = (FooterVO)data;
        String text = String.format(Locale.CHINA, "by %s\r\n%s", footerVO.getAuthor(), footerVO.getPublishTime());
        SpannableString sp = new SpannableString(text);
        sp.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.indexOf('\r'), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.GRAY), text.indexOf('\r'), text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setText(sp);
    }

    @Override
    protected void setupView() {
        mTextView = itemView.findViewById(R.id.article_text);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimensionPixelSize(R.dimen.dimen_42px));
        mTextView.setLines(2);
    }
}
