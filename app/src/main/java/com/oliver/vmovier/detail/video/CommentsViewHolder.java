package com.oliver.vmovier.detail.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.core.utils.Logger;
import com.squareup.picasso.Picasso;

public class CommentsViewHolder extends BaseRVViewHolder {

    private static final String TAG = "CommentsViewHolder";

    private ViewStub mTotalViewStub;
    private AppCompatTextView mTotalTextView;
    private AppCompatImageView mAvatarImageView;
    private AppCompatTextView mUserTextView;
    private AppCompatTextView mPublishTimeTextView;
    private AppCompatTextView mContentTextView;
    private AppCompatTextView mApproveTextView;
    private RecyclerView mSubComments;
    private BaseRVAdapter mAdapter;


    public CommentsViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        CommentsVO commentsVO = (CommentsVO)data;
        Picasso.get().load(commentsVO.getAvatarUrl()).into(mAvatarImageView);
        mUserTextView.setText(commentsVO.getUserName());
        mPublishTimeTextView.setText(commentsVO.getPublishTime());
        mContentTextView.setText(commentsVO.getContent());
        mApproveTextView.setText(String.valueOf(commentsVO.getApproveCount()));
        fillTotalCount(commentsVO);
        fillSubComments(commentsVO);
    }

    @Override
    protected void setupView() {
        mTotalViewStub = itemView.findViewById(R.id.video_comment_total_view_stub);
        mAvatarImageView = itemView.findViewById(R.id.video_comment_avatar);
        mUserTextView = itemView.findViewById(R.id.video_comment_user_name);
        mPublishTimeTextView = itemView.findViewById(R.id.video_comment_publish_time);
        mContentTextView = itemView.findViewById(R.id.video_comment_content);
        mApproveTextView = itemView.findViewById(R.id.video_comment_approve_count);

        mSubComments = itemView.findViewById(R.id.video_comment_sub_comment);
        if (null != mSubComments) {
            mSubComments.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            mAdapter = new BaseRVAdapter(mContext, createFactory());
            mSubComments.setAdapter(mAdapter);
        }
    }

    private IRVViewHolderFactory createFactory() {
        return new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.video_comment_card_item_layout, viewGroup, false);
                return new CommentsViewHolder(mContext, itemView);
            }
        };
    }

    private void fillTotalCount(CommentsVO commentsVO) {
        if (!TextUtils.isEmpty(commentsVO.getTotalCount())) {
            if (null == mTotalTextView) {
                mTotalTextView = mTotalViewStub.inflate().findViewById(R.id.video_comment_total);
            }
            mTotalTextView.setText(commentsVO.getTotalCount());
            mTotalTextView.setVisibility(View.VISIBLE);
        } else if (null != mTotalTextView) {
            mTotalTextView.setVisibility(View.GONE);
        }
    }

    private void fillSubComments(CommentsVO commentsVO) {
        if (null != mSubComments) {
            if (null != commentsVO.getSubCommentList()) {
                mAdapter.refresh(commentsVO.getSubCommentList());
                mSubComments.setVisibility(View.VISIBLE);
            } else {
                mSubComments.setVisibility(View.GONE);
            }
        }
    }
}
