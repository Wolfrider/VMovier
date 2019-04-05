package com.oliver.vmovier.detail.article;

import java.util.HashMap;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.Router;

public class TextViewHolder extends BaseRVViewHolder {

    private static final String TAG = "TextViewHolder";

    private AppCompatTextView mTextView;

    public TextViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindData(@NonNull IRVItemVO data) {
        TextVO textVO = (TextVO)data;
        setAlign(textVO.getTextAlign());
        setTextSize(textVO.getTextSize());
        setTextColor(textVO.getTextColor());
        setText(textVO.getText());
    }

    @Override
    protected void setupView() {
        mTextView = itemView.findViewById(R.id.article_text);
        mTextView.setTextColor(Color.BLACK);
    }

    private void setAlign(String align) {
        if (TextUtils.equals("center", align)) {
            mTextView.setGravity(Gravity.CENTER);
        } else if (TextUtils.equals("left", align)) {
            mTextView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        }
    }

    private void setTextSize(String size) {
        if (TextUtils.isEmpty(size)) {
            int index = size.indexOf("px");
            if (index > -1) {
                int tmp = Integer.valueOf(size.substring(0, index));
                mTextView.setTextSize(tmp);
            }
        }
    }

    private void setTextColor(String color) {
        if (TextUtils.isEmpty(color)) {
            mTextView.setTextColor(Color.BLACK);
        } else {
            mTextView.setTextColor(Color.parseColor(color));
        }
    }

    private void setText(String text) {
        HrefText hrefText = new HrefText(text);
        SpannableString spannableString = new SpannableString(hrefText.getFormatText());
        for (final Entry<Pair<Integer, Integer>, String> entry : hrefText.getLinks().entrySet()) {
            Pair<Integer, Integer> index = entry.getKey();
            Object span;
            if (TextUtils.isEmpty(entry.getValue())) {
                span = new StyleSpan(Typeface.BOLD);
            } else {
                span = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Logger.d(TAG, "onClick " + entry.getValue());
                        new Router.Builder(mContext, NavType.SCHEME)
                            .setContent(entry.getValue()).build().nav();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                };
            }
            spannableString.setSpan(span, index.first, index.second, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        mTextView.setText(spannableString);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private final class HrefText {

        private static final String HREF_TAG_START = "[a";
        private static final String TAG_END = "\"]";
        private static final String HREF_TAG = "href=";
        private static final String CONTENT_TAG = "content=";

        private static final String BOLD_TAG_START = "[b";

        private String mFormatText;

        private HashMap<Pair<Integer, Integer>, String> mLinks;

        public HrefText(String source) {
            mLinks = new HashMap<>(16);
            init(source);
        }

        public String getFormatText() {
            return mFormatText;
        }

        public HashMap<Pair<Integer, Integer>, String> getLinks() {
            return mLinks;
        }

        private void init(String source) {
            StringBuilder builder = new StringBuilder();
            int from = 0;
            do {
                Pair<Integer, Integer> target = findHref(source, from);
                if (null != target) {
                    builder.append(source.substring(from, target.first));
                    Pair<String, String> href = getHref(source, target.first, target.second);
                    int start = builder.toString().length();
                    int end = start + href.second.length();
                    builder.append(href.second);
                    mLinks.put(new Pair<>(start, end), href.first);
                    from = target.second + TAG_END.length();
                    continue;
                }
                target = findBold(source, from);
                if (null != target) {
                    builder.append(source.substring(from, target.first));
                    Pair<String, String> href = getBold(source, target.first, target.second);
                    int start = builder.toString().length();
                    int end = start + href.second.length();
                    builder.append(href.second);
                    mLinks.put(new Pair<>(start, end), href.first);
                    from = target.second + TAG_END.length();
                } else {
                    builder.append(source.substring(from));
                    break;
                }
            } while(true);
            mFormatText = builder.toString();
        }

        private Pair<Integer, Integer> findHref(String source, int from) {
            int startIndex = source.indexOf(HREF_TAG_START, from);
            if (startIndex > -1) {
                int endIndex = source.indexOf(TAG_END, startIndex);
                if (endIndex > -1) {
                    return new Pair<>(startIndex, endIndex);
                }
            }
            return null;
        }

        private Pair<String, String> getHref(String source, int startIndex, int endIndex) {
            String href = source.substring(startIndex, endIndex);
            int start = href.indexOf(HREF_TAG);
            int end = href.indexOf(CONTENT_TAG, start);
            String tmp = href.substring(start + HREF_TAG.length(), end);
            String link = tmp.trim().replace("\"", "");
            tmp = href.substring(end + CONTENT_TAG.length());
            String content = tmp.trim().replace("\"", "");
            return new Pair<>(link, content);
        }

        private Pair<Integer, Integer> findBold(String source, int from) {
            int startIndex = source.indexOf(BOLD_TAG_START, from);
            if (startIndex > -1) {
                int endIndex = source.indexOf(TAG_END, startIndex);
                if (endIndex > -1) {
                    return new Pair<>(startIndex, endIndex);
                }
            }
            return null;
        }

        private Pair<String, String> getBold(String source, int startIndex, int endIndex) {
            String bold = source.substring(startIndex, endIndex);
            int start = bold.indexOf(CONTENT_TAG, startIndex);
            int end = bold.indexOf(TAG_END, start);
            if (end < 0) {
                end = endIndex;
            }
            String tmp = bold.substring(start + CONTENT_TAG.length(), end);
            String content = tmp.trim().replace("\"", "");
            return new Pair<>("", content);
        }
    }



}
