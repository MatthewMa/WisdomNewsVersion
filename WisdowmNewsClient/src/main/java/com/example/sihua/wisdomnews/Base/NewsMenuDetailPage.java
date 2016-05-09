package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sihua.wisdomnews.Bean.NewsData;
import com.example.sihua.wisdomnews.CustomedView.HorizontalViewPager;
import com.example.sihua.wisdomnews.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻菜单详情页
 */
public class NewsMenuDetailPage extends BaseMenuDetailPage {

    private HorizontalViewPager vp_content;
    private List<NewsData.NewsMenuData.NewsTabData> children;
    public NewsMenuDetailPage(Activity activity) {
        super(activity);
    }
    private ArrayList<TabDetail> myPagerList;
    private TabPageIndicator tp_title;
    private ImageButton ib_next;
    public NewsMenuDetailPage(Activity mActivity, List<NewsData.NewsMenuData.NewsTabData> children) {
        super(mActivity);
        this.children=children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.menu_news_detail, null);
        vp_content= (HorizontalViewPager) view.findViewById(R.id.vp_content);
        //初始化自定义控件
        tp_title= (TabPageIndicator) view.findViewById(R.id.tp_title);
        ib_next= (ImageButton) view.findViewById(R.id.ib_next);
        //跳转至下一个页面
        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = vp_content.getCurrentItem();
                currentItem++;
                vp_content.setCurrentItem(currentItem);
            }
        });
        return view;
    }

    //初始化页签数据
    @Override
    public void initData() {
        myPagerList=new ArrayList<TabDetail>();
        for (int i = 0; i < children.size(); i++) {
            TabDetail td=new TabDetail(mActivity,children.get(i));
            myPagerList.add(td);
        }
        vp_content.setAdapter(new MenuDetailAdapter());
        //必须在VIEWPAGER设置完ADAPTER后才能调用
        tp_title.setViewPager(vp_content);
    }

    class MenuDetailAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return myPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetail currentPager=myPagerList.get(position);
            container.addView(currentPager.mRootView);
            currentPager.initData();
            return currentPager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return children.get(position).getTitle();
        }
    }
}
