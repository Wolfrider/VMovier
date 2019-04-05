package com.oliver.vmovier.view.banner;

import android.support.annotation.NonNull;
import android.view.View;

public abstract class AutoBannerViewHolder {

    public abstract View getView();

    public abstract void onBindData(@NonNull Object args);

}
