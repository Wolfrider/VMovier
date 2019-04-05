package com.oliver.vmovier.detail.video;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.oliver.vmovier.R;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;
import com.squareup.picasso.Picasso;

public class RelateVideoAdapter extends PagerAdapter {

    private static final String TAG = "RelateVideoAdapter";

    private Context mContext;
    private List<PostVO> mData;

    public RelateVideoAdapter(@NonNull Context context) {
        mContext = context;
        mData = new ArrayList<>(16);
    }

    public void refresh(List<PostVO> newData) {
        mData.clear();
        mData.addAll(newData);
        notifyDataSetChanged();
        Logger.d(TAG, "refresh data size = " + mData.size());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.post_relate_item, container, false);
        fillData(itemView, position);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.46F;
    }

    private void fillData(View itemView, int position) {
        Logger.d(TAG, "fillData position = " + position);
        final PostVO postVO = mData.get(position);
        AppCompatImageView imageView = itemView.findViewById(R.id.post_list_image);
        Picasso.get().load(postVO.getImageUrl()).into(imageView);
        AppCompatTextView titleTextView = itemView.findViewById(R.id.post_list_title);
        titleTextView.setText(postVO.getTitle());
        AppCompatTextView subTitleTextView = itemView.findViewById(R.id.post_list_subtitle);
        subTitleTextView.setText(postVO.getSubTitle());
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "onClick " + postVO.getImageUrl());
                new Router.Builder(mContext, NavType.VIDEO)
                    .setContent(postVO.getId())
                    .build().nav();
            }
        });
    }
}
