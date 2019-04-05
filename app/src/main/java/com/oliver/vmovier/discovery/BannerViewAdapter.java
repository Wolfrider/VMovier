package com.oliver.vmovier.discovery;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView.ScaleType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.view.banner.AutoBannerAdapter;
import com.oliver.vmovier.view.banner.AutoBannerViewHolder;
import com.squareup.picasso.Picasso;

public class BannerViewAdapter<TData, TViewHolder extends AutoBannerViewHolder> extends AutoBannerAdapter {

    private Context mContext;
    private List<TData> mData;
    private Class<TViewHolder> mViewHolderClass;

    public BannerViewAdapter(@NonNull Context context, Class<TViewHolder> viewHolderClass) {
        super();
        mContext = context;
        mData = new LinkedList<>();
        mViewHolderClass = viewHolderClass;
    }

    public void setData(@NonNull List<TData> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getRealPosition(int position) {
        return position % mData.size();
    }

    @Override
    public int getRealCount() {
        return mData.size();
    }

    @Override
    public int getCount() {
        return mData.size() != 0 ? Integer.MAX_VALUE: 0;
    }

    @Override
    public AutoBannerViewHolder onCreateViewHolder() {
        try {
            Constructor<?> constructor = mViewHolderClass.getConstructor(Context.class);
            return (AutoBannerViewHolder)constructor.newInstance(mContext);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void onBindViewHolder(AutoBannerViewHolder viewHolder, int position) {
        viewHolder.onBindData(mData.get(position));
    }
}
