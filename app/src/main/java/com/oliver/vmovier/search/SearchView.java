package com.oliver.vmovier.search;

import java.util.ArrayList;
import java.util.List;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.core.Constant.CardType;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.post.PostViewHolder;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.UIUtils;
import com.oliver.vmovier.search.FilterAdapter.OnDropDownListener;
import com.oliver.vmovier.view.dropdown.DropDownMenu;
import com.oliver.vmovier.view.dropdown.DropDownMenu.OnDropDownMenuListener;
import com.oliver.vmovier.view.dropdown.DropDownMenuAdapter;

public class SearchView extends BaseView {

    private static final String TAG = "SearchView";

    private SearchViewModel mViewModel;

    private AppCompatEditText mInputEditText;
    private AppCompatImageView mInputCloseButton;
    private LinearLayout mRecommendLayout;
    private DropDownMenu mFilterDropDown;
    private RecyclerView mResultView;

    private BaseRVAdapter mRecommendAdapter;
    private BaseRVAdapter mResultAdapter;
    private FilterAdapter mFilterAdapter;

    private boolean mIsLoading = false;

    private RecommendViewHolder.OnClickListener mOnClickListener = new RecommendViewHolder.OnClickListener() {
        @Override
        public void onClick(String tag) {
            mInputEditText.setText(tag);
            mViewModel.getSearch(tag);
            UIUtils.hideKeyboard(mInputEditText);
        }
    };

    public SearchView(@NonNull AppCompatActivity activity) {
        super(activity);
        mViewModel = ViewModelProviders.of(mActivity).get(SearchViewModel.class);
    }

    @Override
    public void init() {
        setupView();
        setupData();
    }

    private void setupView() {
        setupInputView();
        setupRecommendView();
        setupFilterView();
        setupResultView();
    }

    private void setupInputView() {
        mInputEditText = mActivity.findViewById(R.id.search_input);
        mInputEditText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (null != mInputEditText.getText()) {
                        String tag = mInputEditText.getText().toString();
                        Logger.d(TAG, "search input " + tag);
                        mViewModel.getSearch(tag);
                        UIUtils.hideKeyboard(mInputEditText);
                    }
                    return true;
                }
                return false;
            }
        });
        mInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    mInputCloseButton.setVisibility(View.INVISIBLE);
                } else {
                    mInputCloseButton.setVisibility(View.VISIBLE);
                }
            }
        });
        mActivity.findViewById(R.id.search_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mInputEditText.getText()) {
                    String tag = mInputEditText.getText().toString();
                    Logger.d(TAG, "search input " + tag);
                    mViewModel.getSearch(tag);
                    UIUtils.hideKeyboard(mInputEditText);
                }
            }
        });
        mInputCloseButton = mActivity.findViewById(R.id.search_input_close);
        mInputCloseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputEditText.setText("");
                mRecommendLayout.setVisibility(View.VISIBLE);
                mFilterDropDown.setVisibility(View.GONE);
                mResultView.setVisibility(View.GONE);
            }
        });
    }

    private void setupFilterView() {
        mFilterDropDown = mActivity.findViewById(R.id.search_filter);
        mFilterAdapter = new FilterAdapter(mActivity, new OnDropDownListener() {
            @Override
            public void onSelected(String value, int position) {
                mFilterDropDown.setTitle(value, position);
                mFilterDropDown.closeMenu();
                Logger.d(TAG, "select Filter " + value);
                mViewModel.getSearchByFilter(value, position);
            }
        });
        mFilterDropDown.setAdapter(mFilterAdapter);
        mFilterDropDown.setOnDropDownMenuListener(new OnDropDownMenuListener() {
            @Override
            public void onPopupViewChanged(boolean isShowing) {
                mResultView.setVisibility(isShowing ? View.GONE : View.VISIBLE);
            }
        });
        mFilterDropDown.setVisibility(View.GONE);
    }

    private void setupRecommendView() {
        mRecommendLayout = mActivity.findViewById(R.id.search_recommend_container);
        RecyclerView recyclerView = mActivity.findViewById(R.id.search_recommend_tags);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mActivity);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);
        mRecommendAdapter = new BaseRVAdapter(mActivity, new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                switch (viewType) {
                    case CardType.RECOMMEND_WORD:
                        return new RecommendViewHolder(mActivity, new AppCompatTextView(mActivity), mOnClickListener);
                    default:
                        break;
                }
                throw new IllegalArgumentException();
            }
        });
        recyclerView.setAdapter(mRecommendAdapter);
        mRecommendLayout.setVisibility(View.VISIBLE);
    }

    private void setupResultView() {
        mResultView = mActivity.findViewById(R.id.search_result);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mResultView.setLayoutManager(layoutManager);
        final LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        mResultAdapter = new BaseRVAdapter(mActivity, new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                switch (viewType) {
                    case CardType.POST:
                        return new PostViewHolder(context,
                            layoutInflater.inflate(R.layout.post_list_item, viewGroup, false));
                    default:
                        break;
                }
                throw new IllegalArgumentException();
            }
        });
        mResultView.setAdapter(mResultAdapter);
        mResultView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findLastVisibleItemPosition() > layoutManager.getItemCount() - 3) {
                    if (!mIsLoading && mViewModel.canMoreSearch()) {
                        mViewModel.getMoreSearch();
                        mIsLoading = true;
                    }
                }
            }
        });
        mResultView.setVisibility(View.GONE);
    }

    private void setupData() {
        mViewModel.getRecommend().observe(mActivity, new Observer<List<RecommendItemVO>>() {
            @Override
            public void onChanged(@Nullable List<RecommendItemVO> recommendItemVOS) {
                if (null != recommendItemVOS) {
                    mRecommendAdapter.refresh(recommendItemVOS);
                    mRecommendLayout.setVisibility(View.VISIBLE);
                    mFilterDropDown.setVisibility(View.GONE);
                    mResultView.setVisibility(View.GONE);

                    List<List<FilterVO>> data = new ArrayList<>();
                    data.add(mViewModel.getTypes());
                    data.add(mViewModel.getOrders());
                    data.add(mViewModel.getCates());
                    mFilterAdapter.refresh(data);
                }
            }
        });
        mViewModel.getResult().observe(mActivity, new Observer<List<IRVItemVO>>() {
            @Override
            public void onChanged(@Nullable List<IRVItemVO> itemVOS) {
                if (null != itemVOS) {
                    mFilterDropDown.setVisibility(View.VISIBLE);
                    mResultView.setVisibility(View.VISIBLE);
                    mRecommendLayout.setVisibility(View.GONE);
                    if (mIsLoading) {
                        mResultAdapter.append(itemVOS);
                        mIsLoading = false;
                    } else {
                        mResultAdapter.refresh(itemVOS);
                    }
                }
            }
        });
    }
}
