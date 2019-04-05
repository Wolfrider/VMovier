package com.oliver.vmovier.base.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

public interface IRVViewHolderFactory {

    BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType);
}
