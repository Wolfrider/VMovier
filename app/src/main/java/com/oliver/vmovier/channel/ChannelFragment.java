package com.oliver.vmovier.channel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseFragment;
import com.oliver.vmovier.core.utils.Logger;

public class ChannelFragment extends BaseFragment<ChannelView> {

    private static final String TAG = "ChannelFragment";

    public static ChannelFragment newInstance() {
        return new ChannelFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new ChannelView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Logger.trace(TAG);
        View view = inflater.inflate(R.layout.channel_fragment, container, false);
        mView.init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
