package com.oliver.vmovier.view.dropdown;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.oliver.vmovier.R;

public class DropDownMenu extends LinearLayout {

    public interface OnDropDownMenuListener {

        void onPopupViewChanged(boolean isShowing);
    }

    private class DropDownNode {

        private RelativeLayout mTitleContainer;

        private AppCompatImageView mDropDownImageView;

        private AppCompatTextView mTitleTextView;

        private RecyclerView mPopupView;
    }

    private DropDownMenuAdapter mAdapter;
    private SparseArray<DropDownNode> mNodes;
    private int mShowingPopupIndex = -1;

    private OnDropDownMenuListener mOnDropDownMenuListener;

    private Observer mObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            DropDownMenu.this.update();
        }
    };

    public DropDownMenu(Context context) {
        this(context, null);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNodes = new SparseArray<>();
    }

    public void setOnDropDownMenuListener(@NonNull OnDropDownMenuListener listener) {
        mOnDropDownMenuListener = listener;
    }

    public void setAdapter(@NonNull DropDownMenuAdapter adapter) {
        mAdapter = adapter;
        mAdapter.deleteObservers();
        mAdapter.addObserver(mObserver);
    }

    public void setTitle(@NonNull String title, int position) {
        DropDownNode node = mNodes.get(position);
        if (null != node) {
            node.mTitleTextView.setText(title);
        }
    }

    public String getTitle(int position) {
        DropDownNode node = mNodes.get(position);
        if (null != node) {
            return node.mTitleTextView.getText().toString();
        }
        return "";
    }

    public void closeMenu() {
        if (mShowingPopupIndex > -1) {
            mNodes.get(mShowingPopupIndex).mDropDownImageView.setImageResource(R.drawable.drop_down);
            removeView(mNodes.get(mShowingPopupIndex).mPopupView);
            mShowingPopupIndex = -1;
            if (null != mOnDropDownMenuListener) {
                mOnDropDownMenuListener.onPopupViewChanged(false);
            }
        }
    }

    private void update() {
        mNodes.clear();
        removeAllViews();
        setOrientation(LinearLayout.VERTICAL);
        LinearLayout titleLayout = new LinearLayout(getContext());
        titleLayout.setOrientation(HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        for (int i = 0; i < mAdapter.getItemCount(); ++i) {
            DropDownNode node = createNode(i);
            titleLayout.addView(node.mTitleContainer, layoutParams);
            mNodes.put(i, node);
        }
        addView(titleLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    private DropDownNode createNode(final int position) {
        DropDownNode node = new DropDownNode();
        node.mTitleContainer = new RelativeLayout(getContext());
        node.mDropDownImageView = createDropDownImageView();
        node.mTitleTextView = createTitleView(position);
        node.mTitleContainer.addView(node.mDropDownImageView);
        node.mTitleContainer.addView(node.mTitleTextView);
        node.mPopupView = mAdapter.getPopupView(position);
        node.mTitleContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShowingPopupIndex == -1) {
                    mShowingPopupIndex = position;
                    mNodes.get(position).mDropDownImageView.setImageResource(R.drawable.drop_up);
                    LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                    int top = getResources().getDimensionPixelSize(R.dimen.dimen_30px);
                    layoutParams.setMargins(0, top, 0, 0);
                    addView(mNodes.get(position).mPopupView, layoutParams);
                    if (null != mOnDropDownMenuListener) {
                        mOnDropDownMenuListener.onPopupViewChanged(true);
                    }
                } else {
                    closeMenu();
                }
            }
        });
        return node;
    }

    private AppCompatImageView createDropDownImageView() {
        AppCompatImageView imageView = new AppCompatImageView(getContext());
        int size = getResources().getDimensionPixelSize(R.dimen.dimen_72px);
        imageView.setImageResource(R.drawable.drop_down);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size, size);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private AppCompatTextView createTitleView(int position) {
        String title = mAdapter.getItemTitle(position);
        AppCompatTextView titleTextView = new AppCompatTextView(getContext());
        titleTextView.setText(title);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.dimen_54px));
        titleTextView.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        titleTextView.setLayoutParams(layoutParams);
        return titleTextView;
    }
}
