package com.example.sihua.wisdomnews.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.sihua.wisdomnews.R;
import com.example.sihua.wisdomnews.fragments.ContentFragment;
import com.example.sihua.wisdomnews.fragments.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HomeActivity extends SlidingFragmentActivity {

    @ViewInject(R.id.fl_menu)
    private FrameLayout fl_menu;
    @ViewInject(R.id.fl_content)
    private FrameLayout fl_content;
    private final static String FRAGMENT_LEFT_MENU="fragment_left_menu";
    private final static String FRAGMENT_CONTENT="fragment_content";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_home);
        ViewUtils.inject(this);
        setBehindContentView(R.layout.left_menu);
        initFragment();
        SlidingMenu sm=getSlidingMenu();
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.setMode(SlidingMenu.LEFT);//设置模式
        sm.setBehindOffset(800);//预留宽度
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_menu, new LeftMenuFragment(), FRAGMENT_LEFT_MENU);
        transaction.replace(R.id.fl_content, new ContentFragment(), FRAGMENT_CONTENT);
        transaction.commit();

    }
    public LeftMenuFragment getLeftMenuFragment(){
        FragmentManager manager=getSupportFragmentManager();
        LeftMenuFragment leftMenuFragment= (LeftMenuFragment) manager.findFragmentByTag(FRAGMENT_LEFT_MENU);
        return leftMenuFragment;
    }

    public ContentFragment getContentFragment(){
        FragmentManager manager=getSupportFragmentManager();
        ContentFragment contentFragment= (ContentFragment) manager.findFragmentByTag(FRAGMENT_CONTENT);
        return contentFragment;
    }
}
