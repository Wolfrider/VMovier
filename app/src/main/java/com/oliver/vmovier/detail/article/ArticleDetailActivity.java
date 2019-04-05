package com.oliver.vmovier.detail.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.core.Constant.IntentAction;

public class ArticleDetailActivity extends BaseActivity<ArticleDetailView> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity);
        Bundle bundle = getIntent().getBundleExtra(IntentAction.PARAM_CONTENT);
        mView = new ArticleDetailView(this, new ArticleVO(bundle));
        mView.init();
    }
}
