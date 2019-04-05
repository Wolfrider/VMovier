package com.oliver.vmovier.home.open;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.oliver.vmovier.R;
import com.oliver.vmovier.core.rxext.AutoObserver;
import com.oliver.vmovier.core.rxext.RxRes;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;
import com.squareup.picasso.Picasso;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class FirstScreenDialog extends DialogFragment {

    private static final String TAG = "FirstScreenDialog";

    private static final String PARAM_TYPE = "CLICK_TYPE";
    private static final String PARAM_CONTENT = "CONTENT";
    private static final String PARAM_IMAGE = "IMAGE";
    private static final String PARAM_DURATION = "DURATION";

    private FirstScreenVO mData;
    private RxRes mRxRes;
    private OnDismissListener mOnDismissListener;

    public static FirstScreenDialog newInstance(@NonNull FirstScreenVO data) {
        FirstScreenDialog dialog = new FirstScreenDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_TYPE, data.getClickType());
        bundle.putString(PARAM_CONTENT, data.getContent());
        bundle.putString(PARAM_IMAGE, data.getImagePath());
        bundle.putInt(PARAM_DURATION, data.getDuration());
        dialog.setArguments(bundle);
        return dialog;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != arguments) {
            mData = new FirstScreenVO();
            mData.setClickType(arguments.getInt(PARAM_TYPE));
            mData.setContent(arguments.getString(PARAM_CONTENT));
            mData.setImagePath(arguments.getString(PARAM_IMAGE));
            mData.setDuration(arguments.getInt(PARAM_DURATION));
        }
        mRxRes = new RxRes();
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppFullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_screen_dialog, container, false);
        fillView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRxRes.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (null != window) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (null != mOnDismissListener) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    private void fillView(View parent) {
        fillImage(parent);
        fillCountDown(parent);
    }

    private void fillImage(View parent) {
        final AppCompatImageView imageView = parent.findViewById(R.id.home_first_screen_image);
        Picasso.get().load(mData.getImagePath()).into(imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "onClick");
                new Router.Builder(getActivity(), mData.getClickType())
                    .setContent(mData.getContent())
                    .build().nav();
                dismiss();
            }
        });
    }

    private void fillCountDown(View parent) {
        final AppCompatTextView countDownTextView = parent.findViewById(R.id.home_first_screen_countdown);
        String duration = String.format(Locale.CHINA, getResources().getString(R.string.count_down_format), mData.getDuration());
        countDownTextView.setText(duration);
        countDownTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "skip");
                dismiss();
            }
        });
        Observable.intervalRange(0, mData.getDuration() + 1, 0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new AutoObserver<>(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    Logger.d(TAG, "onNext " + aLong);
                    long current = mData.getDuration() - aLong;
                    countDownTextView.setText(String.format(Locale.CHINA, getResources().getString(R.string.count_down_format), current));
                }
            }, new Action() {
                @Override
                public void run() throws Exception {
                    Logger.d(TAG, "timeout");
                    dismiss();
                }
            }));
    }


}
