package com.oliver.vmovier.home.open;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.core.utils.Logger;

public class FirstScreenView extends BaseView {

    private static final String TAG = "FirstScreenView";

    public interface OnFinishListener {

        void onFinish();
    }

    private OnFinishListener mOnFinishListener;
    private FirstScreenViewModel mViewModel;

    public FirstScreenView(@NonNull AppCompatActivity activity, @NonNull OnFinishListener listener) {
        super(activity);
        mOnFinishListener = listener;
        mViewModel = ViewModelProviders.of(mActivity).get(FirstScreenViewModel.class);
    }

    @Override
    public void init() {
        FirstScreenVO firstScreenVO = mViewModel.getData();
        if (null != firstScreenVO) {
            Logger.d(TAG, "showFirstScreen, " + firstScreenVO);
            FirstScreenDialog dialog = FirstScreenDialog.newInstance(firstScreenVO);
            dialog.show(mActivity.getSupportFragmentManager(), "FirstScreenDialog");
            dialog.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Logger.d(TAG, "onDismiss");
                    mViewModel.finish();
                    mOnFinishListener.onFinish();
                }
            });
        } else {
            Logger.d(TAG, "no first screen");
            mViewModel.finish();
            mOnFinishListener.onFinish();
        }
    }
}
