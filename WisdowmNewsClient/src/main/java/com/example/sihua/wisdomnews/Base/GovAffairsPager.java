package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 首页实现
 */
public class GovAffairsPager extends BasePager {
    public GovAffairsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_title.setText("人口管理");
        TextView textView=new TextView(mActivity);
        textView.setText("政务");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        setSlidingMenuEnable(true);
        fl_content.addView(textView);//向framelayout中动态添加布局
    }
}
