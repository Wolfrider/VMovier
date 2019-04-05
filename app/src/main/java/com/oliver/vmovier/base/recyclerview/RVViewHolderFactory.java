package com.oliver.vmovier.base.recyclerview;

import java.lang.reflect.Constructor;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@Deprecated
public class RVViewHolderFactory {

    private static class ViewHolderItem {
        private Class<? extends BaseRVViewHolder> mViewHolderClass;

        private int mLayoutResId;

        private ViewHolderItem(Class<? extends BaseRVViewHolder> clazz, int layoutResId) {
            mViewHolderClass = clazz;
            mLayoutResId = layoutResId;
        }
    }

    private SparseArray<ViewHolderItem> mViewHolders;

    public RVViewHolderFactory() {
        mViewHolders = new SparseArray<>();
    }

    public void register(int viewType, @NonNull Class<? extends BaseRVViewHolder> clazz,
                         @LayoutRes int layoutResId) {
        mViewHolders.put(viewType, new ViewHolderItem(clazz, layoutResId));
    }

    public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
        ViewHolderItem item = mViewHolders.get(viewType);
        if (null != item) {
            View view = LayoutInflater.from(context).inflate(item.mLayoutResId, viewGroup, false);
            try {
                //TODO:creator function
                Constructor<?> constructor =item.mViewHolderClass.getConstructor(Context.class, View.class);
                return (BaseRVViewHolder)constructor.newInstance(context, view);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        throw new IllegalArgumentException();
    }

}
