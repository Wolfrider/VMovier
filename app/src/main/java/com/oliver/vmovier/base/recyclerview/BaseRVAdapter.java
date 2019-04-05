package com.oliver.vmovier.base.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class BaseRVAdapter extends RecyclerView.Adapter<BaseRVViewHolder> {

    protected Context mContext;
    protected IRVViewHolderFactory mFactory;
    protected List<IRVItemVO> mData;

    public BaseRVAdapter(@NonNull Context context, @NonNull IRVViewHolderFactory factory) {
        mContext = context;
        mFactory = factory;
        mData = new ArrayList<>(32);
    }

    @NonNull
    @Override
    public BaseRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return mFactory.newInstance(mContext, viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRVViewHolder baseViewHolder, int position) {
        baseViewHolder.onBindData(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseRVViewHolder holder) {
        holder.onViewAttach();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseRVViewHolder holder) {
        holder.onViewDetach();
    }

    public void refresh(@NonNull List<? extends IRVItemVO> newData) {
        mData.clear();
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public void append(@NonNull List<? extends IRVItemVO> newData) {
        int start = mData.size();
        mData.addAll(newData);
        notifyItemRangeInserted(start, newData.size());
    }

    public void insert(@NonNull List<? extends IRVItemVO> newData) {
        mData.addAll(0, newData);
        notifyItemRangeInserted(0, newData.size());
    }
}
