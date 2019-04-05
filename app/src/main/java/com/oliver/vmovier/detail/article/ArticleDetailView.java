package com.oliver.vmovier.detail.article;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.core.Constant.CardType.ArticleDetail;

public class ArticleDetailView extends BaseView {

    private static final String TAG = "ArticleDetailView";

    private ArticleVO mArticleVO;

    private AppCompatTextView mTitleTextView;
    private BaseRVAdapter mAdapter;

    public ArticleDetailView(@NonNull AppCompatActivity activity, @NonNull ArticleVO articleVO) {
        super(activity);
        mArticleVO = articleVO;
    }

    @Override
    public void init() {
        setupView();
    }

    private void setupView() {
        mActivity.findViewById(R.id.toolbar_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        mTitleTextView = mActivity.findViewById(R.id.toolbar_title);
        mTitleTextView.setText(mArticleVO.getTitle());
        mTitleTextView.setVisibility(View.GONE);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = mActivity.findViewById(R.id.article_content);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new BaseRVAdapter(mActivity, createFactory());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstVisibleItemPosition() > 0) {
                    mTitleTextView.setVisibility(View.VISIBLE);
                } else {
                    mTitleTextView.setVisibility(View.GONE);
                }
            }
        });
        mAdapter.refresh(mArticleVO.getContents());
    }

    private IRVViewHolderFactory createFactory() {
        final LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        return new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                switch (viewType) {
                    case ArticleDetail.HEADER:
                        return new HeaderViewHolder(context,
                            layoutInflater.inflate(R.layout.article_text_item, viewGroup, false));
                    case ArticleDetail.FOOTER:
                        return new FooterViewHolder(context,
                            layoutInflater.inflate(R.layout.article_text_item, viewGroup, false));
                    case ArticleDetail.TEXT:
                        return new TextViewHolder(context,
                            layoutInflater.inflate(R.layout.article_text_item, viewGroup, false));
                    case ArticleDetail.IMAGE:
                        return new ImageViewHolder(context,
                            layoutInflater.inflate(R.layout.article_image_item, viewGroup, false));
                    default:
                        break;
                }
                throw new IllegalArgumentException();
            }
        };
    }

}
