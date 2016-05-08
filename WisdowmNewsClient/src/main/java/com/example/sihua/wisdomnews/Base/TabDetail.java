package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.sihua.wisdomnews.Bean.NewsData;

/**
 * Created by Sihua on 2016/5/7.
 */
public class TabDetail extends BaseMenuDetailPage {

    private NewsData.NewsMenuData.NewsTabData dataInfo;
    private TextView textView;
    public TabDetail(Activity activity) {
        super(activity);
    }

    public TabDetail(Activity mActivity, NewsData.NewsMenuData.NewsTabData newsTabData) {
        super(mActivity);
        this.dataInfo=newsTabData;
    }

    @Override
    public View initView() {
        textView=new TextView(mActivity);
        textView.setText("页签详情页");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        return textView;
    }

    @Override
    public void initData() {
        //展示数据
        textView.setText(dataInfo.getTitle());
    }
}
