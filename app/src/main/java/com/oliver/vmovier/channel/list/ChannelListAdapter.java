package com.oliver.vmovier.channel.list;

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
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.DiffCallback;
import com.oliver.vmovier.core.post.PostItemCallback;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.post.PostViewHolder;
import com.oliver.vmovier.core.utils.Logger;

@Deprecated
public class ChannelListAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private static final String TAG = "ChannelListAdapter";

    private Context mContext;
    private List<PostVO> mPostList;

    public ChannelListAdapter(@NonNull Context context) {
        mContext = context;
        mPostList = new LinkedList<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.post_list_item, viewGroup, false);
        return new PostViewHolder(mContext, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        PostVO data = mPostList.get(i);
        postViewHolder.onBindData(data);
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull PostViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Logger.d(TAG, "onViewAttachedToWindow " + holder.getAdapterPosition());
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PostViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Logger.d(TAG, "onViewDetachedFromWindow " + holder.getAdapterPosition());
    }

    public void update(List<PostVO> newData) {
        Logger.d(TAG, String.format(Locale.US, "update oldSize = %d, newSize = %d", mPostList.size(), newData.size()));
        DiffCallback<PostVO> diffCallback = new DiffCallback<>(mPostList, newData, new PostItemCallback());
        DiffResult result = DiffUtil.calculateDiff(diffCallback, true);
        result.dispatchUpdatesTo(this);
        mPostList = new LinkedList<>(newData);
    }
}
