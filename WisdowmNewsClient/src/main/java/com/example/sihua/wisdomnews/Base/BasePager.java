package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sihua.wisdomnews.R;
import com.example.sihua.wisdomnews.activities.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 主页下5个子页面的基类
 */
public class BasePager {

    public Activity mActivity;
    public View mRootView;
    public TextView tv_title;
    public FrameLayout fl_content;
    public ImageButton ib_menu;

    public BasePager(Activity activity){
        this.mActivity=activity;
        initViews();//初始化布局文件
    }
    public void initViews(){

        mRootView = View.inflate(mActivity, R.layout.base_pager, null);
        tv_title= (TextView) mRootView.findViewById(R.id.tv_title);
        fl_content= (FrameLayout) mRootView.findViewById(R.id.fl_content);
        ib_menu= (ImageButton) mRootView.findViewById(R.id.ib_menu);
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
    }

    private void toggleSlidingMenu() {
        HomeActivity home= (HomeActivity) mActivity;
        home.getSlidingMenu().toggle();//切换状态,显示时隐藏，隐藏时显示
    }

    public void initData(){

    }

    /**
     * 设置侧边栏开启或关闭
     * @param flag
     */
    public void setSlidingMenuEnable(Boolean flag){
        HomeActivity home= (HomeActivity) mActivity;
        if(flag){
            home.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            home.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
