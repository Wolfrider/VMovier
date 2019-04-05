package com.oliver.vmovier.view.player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.oliver.vmovier.R;

public class PlayHeaderBar {

    public interface OnPlayHeaderListener {

        void onBackClick();
    }

    private Context mContext;
    private RelativeLayout mContainer;
    private View mView;

    private OnPlayHeaderListener mOnPlayHeaderListener;

    public PlayHeaderBar(@NonNull Context context, @NonNull RelativeLayout container) {
        mContext = context;
        mContainer = container;
        init();
    }

    public View getView() {
        return mView;
    }

    public void setOnPlayHeaderListener(OnPlayHeaderListener listener) {
        mOnPlayHeaderListener = listener;
    }

    private void init() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_header_bar, mContainer, false);
        mView.findViewById(R.id.play_header_back_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnPlayHeaderListener) {
                    mOnPlayHeaderListener.onBackClick();
                }
            }
        });
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
            mContext.getResources().getDimensionPixelOffset(R.dimen.dimen_72px));
        layoutParams.setMargins(0, mContext.getResources().getDimensionPixelSize(R.dimen.dimen_18px), 0, 0);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mContainer.addView(mView, layoutParams);
    }

}
