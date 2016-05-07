package com.example.sihua.wisdomnews;

import android.app.ActionBar;
import android.app.Activity;
import android.print.PageRange;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;
    @ViewInject(R.id.ll_page)
    private LinearLayout ll_page;
    @ViewInject(R.id.btn_start)
    private Button btn_start;
    @ViewInject(R.id.rl_page)
    private RelativeLayout rl_page;
    @ViewInject(R.id.view_red)
    private View view_red;
    private int[] images=new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
    List<ImageView> imagelist;
    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        vp_content.setOnPageChangeListener(new MyPageChangeListener());
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        ViewUtils.inject(this);
        imagelist=new ArrayList<ImageView>();
        //init the adapter
        for (int i = 0; i < images.length; i++) {
            ImageView iv=new ImageView(this);
            iv.setBackgroundResource(images[i]);
            imagelist.add(iv);
        }
        for (int i = 0; i < images.length; i++) {
            View view=new ImageView(this);
            view.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(50,50);
            if(i>0){
                params.leftMargin=40;
            }
            view.setLayoutParams(params);
            ll_page.addView(view);
        }
        // 此时拿不到，因为还没有画在界面上
        ViewTreeObserver viewTreeObserver = ll_page.getViewTreeObserver();//获取视图树
        //获取视图树对界面的监听
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当layout运行结束后回调此方法
            @Override
            public void onGlobalLayout() {
                distance = ll_page.getChildAt(1).getLeft()-ll_page.getChildAt(0).getLeft();
                ll_page.getViewTreeObserver().removeOnGlobalLayoutListener(this);//各个版本没问题
            }
        });
        //set adapter
        vp_content.setAdapter(new MyAdapter());
    }

    public class MyAdapter extends PagerAdapter{

        private ImageView currentView;

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            currentView = imagelist.get(position);
            container.addView(currentView);
            //change point color

            return currentView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imagelist.get(position));
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        //滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //百分比会归零
            float len=distance*positionOffset+position*distance;
            //获取布局参数
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view_red.getLayoutParams();
            layoutParams.leftMargin=(int)len;//设置左边距
            view_red.setLayoutParams(layoutParams);
        }

        //页面被选中
        @Override
        public void onPageSelected(int position) {
            if(position==images.length-1){
                btn_start.setVisibility(View.VISIBLE);
            }else{
                btn_start.setVisibility(View.INVISIBLE);
            }
        }

        //滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
