package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.view.View;

/**
 * 菜单详情页
 */
public abstract class BaseMenuDetailPage {
    public Activity mActivity;
    public View mRootView;
    public BaseMenuDetailPage(Activity activity){
        this.mActivity=activity;
        mRootView=initView();
    }
    public abstract View initView();
    public void initData(){

    }
}
