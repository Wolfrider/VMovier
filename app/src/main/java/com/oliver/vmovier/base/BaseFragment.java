package com.oliver.vmovier.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oliver.vmovier.base.BaseFragment.BaseView;
import com.oliver.vmovier.core.utils.Logger;

public abstract class BaseFragment<T extends BaseView> extends Fragment {

    public static abstract class BaseView {

        protected Fragment mFragment;

        protected View mView;

        public BaseView(@NonNull Fragment fragment) {
            mFragment = fragment;
        }

        public void init(@NonNull View view) {
            mView = view;
        }
    }

    protected T mView;

    protected void onVisibleChange(boolean isVisibleToUser) {
        Logger.d(getClass().getSimpleName(), "onVisibleChange " + isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.trace(getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Logger.trace(getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.trace(getClass().getSimpleName());
        onVisibleChange(getUserVisibleHint());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.trace(getClass().getSimpleName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (null != getView()) {
            onVisibleChange(isVisibleToUser);
        }
    }
}
