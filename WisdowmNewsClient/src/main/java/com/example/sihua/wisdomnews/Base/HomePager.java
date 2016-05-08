package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.sihua.wisdomnews.activities.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 首页实现
 */
public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_title.setText("智慧北京");
        TextView textView=new TextView(mActivity);
        textView.setText("首页");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        ib_menu.setVisibility(View.INVISIBLE);
        setSlidingMenuEnable(false);
        fl_content.addView(textView);//向framelayout中动态添加布局
    }


}
