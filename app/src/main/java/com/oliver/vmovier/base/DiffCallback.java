package com.oliver.vmovier.base;

import java.util.List;
import java.util.Locale;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.ItemCallback;
import com.oliver.vmovier.core.utils.Logger;

public class DiffCallback<T> extends DiffUtil.Callback {

    private static final String TAG = "DiffCallback";

    private List<T> mOldData;
    private List<T> mNewData;
    private ItemCallback<T> mItemCallback;

    public DiffCallback(@NonNull List<T> oldData, @NonNull List<T> newData, @NonNull ItemCallback<T> itemCallback) {
        mOldData = oldData;
        mNewData = newData;
        mItemCallback = itemCallback;
    }

    @Override
    public int getOldListSize() {
        Logger.d(TAG, "getOldListSize " + mOldData.size());
        return mOldData.size();
    }

    @Override
    public int getNewListSize() {
        Logger.d(TAG, "getNewListSize " + mNewData.size());
        return mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        Logger.d(TAG, String.format(Locale.US, "areItemsTheSame oldIndex = %d, newIndex = %d", i, i1));
        return i == i1 && mItemCallback.areItemsTheSame(mOldData.get(i), mNewData.get(i1));
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        Logger.d(TAG, String.format(Locale.US, "areContentsTheSame oldIndex = %d, newIndex = %d", i, i1));
        return mItemCallback.areContentsTheSame(mOldData.get(i), mNewData.get(i1));
    }
}
