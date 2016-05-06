package com.example.sihua.wisdomnews;

import android.app.Activity;
import android.print.PageRange;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

    private int[] images=new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
    List<ImageView> imagelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
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
            return currentView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imagelist.get(position));
        }
    }
}
