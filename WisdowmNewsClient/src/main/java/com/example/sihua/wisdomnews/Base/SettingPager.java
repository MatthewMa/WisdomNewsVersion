package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 首页实现
 */
public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_title.setText("设置");
        TextView textView=new TextView(mActivity);
        textView.setText("设置");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        ib_menu.setVisibility(View.INVISIBLE);
        setSlidingMenuEnable(false);
        fl_content.addView(textView);//向framelayout中动态添加布局
    }
}
