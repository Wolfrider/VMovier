package com.oliver.vmovier.base.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.oliver.vmovier.core.utils.Logger;

public abstract class BaseRVViewHolder extends RecyclerView.ViewHolder {

    protected Context mContext;

    public BaseRVViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(itemView);
        mContext = context;
        setupView();
    }

    public abstract void onBindData(@NonNull IRVItemVO data);

    protected abstract void setupView();

    public void onViewAttach() {
        Logger.trace(getClass().getSimpleName());
    }

    public void onViewDetach() {
        Logger.trace(getClass().getSimpleName());
    }
}
