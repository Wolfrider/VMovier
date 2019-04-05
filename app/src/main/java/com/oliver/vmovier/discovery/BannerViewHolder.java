package com.oliver.vmovier.discovery;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.view.banner.AutoBannerView;
import com.oliver.vmovier.view.banner.AutoBannerViewHolder;

public class BannerViewHolder<TData extends IRVItemVO, TViewHolder extends AutoBannerViewHolder> extends
    BaseRVViewHolder {

    private static final String TAG = "BannerViewHolder";

    private AutoBannerView mBannerView;
    private BannerViewAdapter<TData, TViewHolder> mAdapter;

    public BannerViewHolder(@NonNull Context context, @NonNull View itemView, Class<TViewHolder> viewHolderClass) {
        super(context, itemView);
        setupView(viewHolderClass);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        Logger.d(TAG, "onBindData");
        mAdapter.setData(convertData(data));
        mBannerView.setCurrentPosition(mAdapter.getRealCount() * 1000);
        mBannerView.setAutoScroll(true);
        mBannerView.setInterval(3000);
    }

    @Override
    protected void setupView() {
    }

    protected void setupView(Class<TViewHolder> viewHolderClass) {
        mBannerView = itemView.findViewById(R.id.discovery_banner);
        mAdapter = new BannerViewAdapter<>(mContext, viewHolderClass);
        mBannerView.setAdapter(mAdapter);
    }

    @SuppressWarnings("unchecked")
    private List<TData> convertData(IRVItemVO data) {
        CompositeVO vo = (CompositeVO)data;
        List<TData> items = new ArrayList<>(vo.getItems().size());
        for(IRVItemVO item: vo.getItems()) {
            items.add((TData)item);
        }
        Logger.d(TAG, "convertData size = " + items.size());
        return items;
    }
}
