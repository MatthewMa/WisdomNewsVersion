package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sihua.wisdomnews.Bean.NewsData;
import com.example.sihua.wisdomnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻菜单详情页
 */
public class NewsMenuDetailPage extends BaseMenuDetailPage {

    private ViewPager vp_content;
    private List<NewsData.NewsMenuData.NewsTabData> children;
    public NewsMenuDetailPage(Activity activity) {
        super(activity);
    }
    private ArrayList<TabDetail> myPagerList;

    public NewsMenuDetailPage(Activity mActivity, List<NewsData.NewsMenuData.NewsTabData> children) {
        super(mActivity);
        this.children=children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.menu_news_detail, null);
        vp_content= (ViewPager) view.findViewById(R.id.vp_content);
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
    }
}
