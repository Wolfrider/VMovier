package com.oliver.vmovier.detail.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.core.Constant.IntentAction;

public class VideoDetailActivity extends BaseActivity<VideoDetailView> {

    private static final String VIDEO_PATH = "video/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        String id = "";
        Intent intent = getIntent();
        if (TextUtils.equals(Intent.ACTION_VIEW, intent.getAction())) {
            String data = intent.getDataString();
            if (!TextUtils.isEmpty(data)) {
                id = data.substring(data.indexOf(VIDEO_PATH) + VIDEO_PATH.length());
            }
        } else {
            id = intent.getStringExtra(IntentAction.PARAM_CONTENT);
        }
        mView = new VideoDetailView(this, id);
        mView.init();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView.release();
    }
}
