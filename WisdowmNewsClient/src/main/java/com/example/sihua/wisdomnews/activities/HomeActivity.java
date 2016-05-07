package com.example.sihua.wisdomnews.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.sihua.wisdomnews.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_home);
        setBehindContentView(R.layout.left_menu);
        SlidingMenu sm=getSlidingMenu();
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.setMode(SlidingMenu.LEFT);//设置模式
        sm.setBehindOffset(800);//预留宽度
    }
}
