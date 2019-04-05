package com.oliver.vmovier.discovery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseFragment;
import com.oliver.vmovier.core.utils.Logger;

public class DiscoveryFragment extends BaseFragment<DiscoveryView> {

    private static final String TAG = "DiscoveryFragment";

    public static DiscoveryFragment newInstance() {
        return new DiscoveryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new DiscoveryView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Logger.trace(TAG);
        View view = inflater.inflate(R.layout.discovery_fragment, container, false);
        mView.init(view);
        return view;
    }
}
