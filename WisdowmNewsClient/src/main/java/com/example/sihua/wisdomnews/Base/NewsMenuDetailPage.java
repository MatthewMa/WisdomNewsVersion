package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sihua.wisdomnews.R;

/**
 * 新闻菜单详情页
 */
public class NewsMenuDetailPage extends BaseMenuDetailPage {

    private ViewPager vp_content;
    public NewsMenuDetailPage(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.menu_news_detail, null);
        vp_content= (ViewPager) view.findViewById(R.id.vp_content);
        return view;
    }

    @Override
    public void initData() {

    }

    class MenuDetailAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
