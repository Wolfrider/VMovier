package com.oliver.vmovier.channel;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.DiffResult;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.DiffCallback;
import com.oliver.vmovier.core.utils.Logger;

@Deprecated
public class ChannelAdapter extends RecyclerView.Adapter<ChannelViewHolder> {

    private static final String TAG = "ChannelAdapter";

    private Context mContext;
    private List<ChannelVO> mChannelVOList;

    public ChannelAdapter(@NonNull Context context) {
        mContext = context;
        mChannelVOList = new LinkedList<>();
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.channel_card_item, viewGroup, false);
        return new ChannelViewHolder(mContext, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder channelViewHolder, int i) {
        ChannelVO channelVO = mChannelVOList.get(channelViewHolder.getAdapterPosition());
        channelViewHolder.onBindData(channelVO);
    }

    @Override
    public int getItemCount() {
        Logger.d(TAG, "getItemCount " + mChannelVOList.size());
        return mChannelVOList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ChannelViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Logger.d(TAG, "onViewAttachedToWindow " + holder.getAdapterPosition());
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ChannelViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Logger.d(TAG, "onViewDetachedFromWindow " + holder.getAdapterPosition());
    }

    public void updateData(@NonNull List<ChannelVO> newData) {
        Logger.d(TAG, "updateData, newData size = " + newData.size());
        mChannelVOList = newData;
        notifyDataSetChanged();
        //DiffCallback<ChannelVO> diffCallback = new DiffCallback<>(mChannelVOList, newData, new ChannelItemCallback());
        //DiffResult result = DiffUtil.calculateDiff(diffCallback, false);
        //mChannelVOList = newData;
        //result.dispatchUpdatesTo(this);
    }
}
