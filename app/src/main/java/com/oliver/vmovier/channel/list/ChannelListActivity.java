package com.oliver.vmovier.channel.list;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.channel.ChannelVO;
import com.oliver.vmovier.core.Constant.CateType;
import com.oliver.vmovier.core.Constant.IntentAction;
import com.oliver.vmovier.core.Constant.NavType;

public class ChannelListActivity extends BaseActivity<ChannelListView> {

    private static final String TAG_PATH = "tag/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list_activity);

        ChannelVO channelVO = null;
        Intent intent = getIntent();
        if (TextUtils.equals(Intent.ACTION_VIEW, intent.getAction())) {
            String data = intent.getDataString();
            int end = data.lastIndexOf("/");
            String id = data.substring(data.indexOf(TAG_PATH) + TAG_PATH.length(), end);
            String tag = "";
            try {
                tag = URLDecoder.decode(data.substring(end + 1), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            channelVO = new ChannelVO();
            channelVO.setCateName(tag);
            channelVO.setCateNav(id);
            channelVO.setCateType(CateType.TAG);
        } else {
            channelVO = intent.getParcelableExtra(IntentAction.PARAM_CONTENT);
        }
        mView = new ChannelListView(this, channelVO);
        mView.init();
    }
}
