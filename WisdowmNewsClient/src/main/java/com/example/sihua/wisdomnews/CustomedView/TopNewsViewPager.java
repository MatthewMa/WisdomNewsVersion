package com.example.sihua.wisdomnews.CustomedView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Sihua on 2016/5/9.
 */
public class TopNewsViewPager extends ViewPager {

    private float startX;
    private float startY;

    public TopNewsViewPager(Context context) {
        super(context);
    }

    public TopNewsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 1.用户右滑并且是第一个页面，父控件拦截
     * 2.左滑动且是最后一个页面，需要拦截
     * 3.上下滑动父控件拦截
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);//不要拦截,这样保证action_move方法调用
                startX = ev.getRawX();
                startY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getRawX();
                float endY = ev.getRawY();
                if(Math.abs(endX-startX)-Math.abs(endY-startY)>0){
                    //左右滑动
                    if(endX>startX){
                        //右边
                        if(TopNewsViewPager.this.getCurrentItem()==0){
                            getParent().requestDisallowInterceptTouchEvent(false);//要拦截,这样保证action_move方法调用
                        }else{
                            getParent().requestDisallowInterceptTouchEvent(true);//不要拦截,这样保证action_move方法调用
                        }
                    }else{
                        //左边
                        if(getCurrentItem()==getAdapter().getCount()-1){
                            //最后一个
                            getParent().requestDisallowInterceptTouchEvent(false);//要拦截,这样保证action_move方法调用
                        }else{
                            getParent().requestDisallowInterceptTouchEvent(true);//不要拦截,这样保证action_move方法调用
                        }
                    }
                }else{
                    //上下滑动
                    getParent().requestDisallowInterceptTouchEvent(false);//要拦截,这样保证action_move方法调用
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }
}
