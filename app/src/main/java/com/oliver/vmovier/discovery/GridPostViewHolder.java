package com.oliver.vmovier.discovery;

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.post.PostViewHolder;

public class GridPostViewHolder extends BaseRVViewHolder {

    private GridLayout mGridLayout;

    public GridPostViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        mGridLayout.removeAllViews();
        List<IRVItemVO> items = ((CompositeVO)data).getItems();
        if (mGridLayout.getChildCount() != items.size()) {
            mGridLayout.removeAllViews();
            initSubViews(items.size());
        }
        for (int i = 0; i < items.size(); ++i) {
            PostVO itemVO = (PostVO)items.get(i);
            PostViewHolder viewHolder = (PostViewHolder)mGridLayout.getChildAt(i).getTag();
            viewHolder.onBindData(itemVO);
        }
    }

    @Override
    protected void setupView() {
        mGridLayout = itemView.findViewById(R.id.discovery_post_container);
        mGridLayout.setColumnCount(2);
        mGridLayout.setOrientation(GridLayout.HORIZONTAL);
    }

    private void initSubViews(int size) {
        for (int i = 0; i < size; ++i) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.post_grid_item, null);
            PostViewHolder viewHolder = new PostViewHolder(mContext, itemView);
            itemView.setTag(viewHolder);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 2, 1);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 2, 1, 1);
            GridLayout.LayoutParams layoutParams = new LayoutParams(rowSpec, columnSpec);
            layoutParams.width = LayoutParams.WRAP_CONTENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            mGridLayout.addView(itemView, layoutParams);
        }
    }

}
