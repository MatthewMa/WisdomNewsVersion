package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 新闻菜单详情页
 */
public class TopicMenuDetailPage extends BaseMenuDetailPage {

    public TopicMenuDetailPage(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView textView=new TextView(mActivity);
        textView.setText("菜单详情页：专题");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        return textView;
    }
}
