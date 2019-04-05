package com.oliver.vmovier.discovery;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.CardType;
import com.oliver.vmovier.core.post.PostViewHolder;
import com.oliver.vmovier.core.utils.Logger;

@Deprecated
public class DiscoveryAdapter extends RecyclerView.Adapter<BaseRVViewHolder> {

    private static final String TAG = "DiscoveryAdapter";

    private Context mContext;
    private List<IRVItemVO> mData;

    public DiscoveryAdapter(@NonNull Context context) {
        super();
        mContext = context;
        mData = new LinkedList<>();
    }

    @NonNull
    @Override
    public BaseRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case CardType.Discovery.BANNER:
                Logger.d(TAG, "onCreateViewHolder BannerViewHolder");
                return new BannerViewHolder<BannerItemVO, SimpleBannerItemViewHolder>(mContext,
                    LayoutInflater.from(mContext).inflate(R.layout.discovery_banner_layout, viewGroup, false), SimpleBannerItemViewHolder.class);
            case CardType.Discovery.TITLE:
                Logger.d(TAG, "onCreateViewHolder TitleViewHolder");
                return new TitleViewHolder(mContext,
                    LayoutInflater.from(mContext).inflate(R.layout.discovery_title_layout, viewGroup, false));
            case CardType.POST:
                Logger.d(TAG, "onCreateViewHolder PostViewHolder");
                return new PostViewHolder(mContext,
                    LayoutInflater.from(mContext).inflate(R.layout.post_list_item, viewGroup, false));
            case CardType.Discovery.GRID_POST:
                Logger.d(TAG, "onCreateViewHolder GridPostViewHolder");
                return new GridPostViewHolder(mContext,
                    LayoutInflater.from(mContext).inflate(R.layout.discovery_grid_post_layout, viewGroup, false));
            case CardType.Discovery.ALBUM:
                Logger.d(TAG, "onCreateViewHolder GridPostViewHolder");
                return new BannerViewHolder<AlbumItemVO, AlbumBannerItemViewHolder>(mContext,
                    LayoutInflater.from(mContext).inflate(R.layout.discovery_banner_layout, viewGroup, false), AlbumBannerItemViewHolder.class);
            default:
                break;
        }
        throw new IllegalStateException();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRVViewHolder baseDiscoveryViewHolder, int position) {
        Logger.d(TAG, "onBindViewHolder " + position);
        baseDiscoveryViewHolder.onBindData(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        Logger.d(TAG, String.format(Locale.US, "getItemViewType position = %d, type = %d", position, mData.get(position).getType()));
        return mData.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void update(@NonNull List<IRVItemVO> newData) {
        Logger.d(TAG, "update size = " + newData.size());
        mData.clear();
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public void append(@NonNull List<IRVItemVO> newData) {
        int positionStart = mData.size();
        mData.addAll(newData);
        notifyItemRangeInserted(positionStart, newData.size());
    }
}
