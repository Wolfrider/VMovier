package com.oliver.vmovier.search;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.search.FilterViewHolder.OnClickListener;
import com.oliver.vmovier.view.dropdown.DropDownMenuAdapter;

public class FilterAdapter extends DropDownMenuAdapter {

    public interface OnDropDownListener {

        void onSelected(String value, int position);
    }

    private Context mContext;
    private List<List<FilterVO>> mData;
    private List<RecyclerView> mRecyclerViews;

    private OnDropDownListener mListener;

    public FilterAdapter(@NonNull Context context, @NonNull OnDropDownListener listener) {
        mContext = context;
        mListener = listener;
        mData = new ArrayList<>(16);
        mRecyclerViews = new ArrayList<>(16);
    }

    public void refresh(List<List<FilterVO>> newData) {
        mData = newData;
        mRecyclerViews.clear();
        for (int i = 0; i < mData.size(); ++i) {
            mRecyclerViews.add(createRecyclerView(i));
        }
        notifyDataChanged();
    }

    @Override
    public RecyclerView getPopupView(int position) {
        return mRecyclerViews.get(position);
    }

    @Override
    public String getItemTitle(int position) {
        return mData.get(position).get(0).getName();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private RecyclerView createRecyclerView(int position) {
        if (position < 2) {
            return createRecyclerViewOfLinear(position);
        } else {
            return createRecyclerViewOfFlex(position);
        }
    }

    private RecyclerView createRecyclerViewOfLinear(final int position) {
        List<FilterVO> filterVOS = mData.get(position);
        RecyclerView recyclerView = new RecyclerView(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        BaseRVAdapter adapter = new BaseRVAdapter(mContext, new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                return new FilterViewHolder(context, new AppCompatTextView(context), new OnClickListener() {
                    @Override
                    public void onClick(String value) {
                        mListener.onSelected(value, position);
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.refresh(filterVOS);
        return recyclerView;
    }

    private RecyclerView createRecyclerViewOfFlex(final int position) {
        List<FilterVO> filterVOS = mData.get(position);
        RecyclerView recyclerView = new RecyclerView(mContext);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);
        BaseRVAdapter adapter = new BaseRVAdapter(mContext, new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                return new FilterTagViewHolder(context, new AppCompatTextView(context), new OnClickListener() {
                    @Override
                    public void onClick(String value) {
                        mListener.onSelected(value, position);
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.refresh(filterVOS);
        return recyclerView;
    }
}
