package com.example.sihua.wisdomnews.fragments;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.sihua.wisdomnews.Base.BasePager;
import com.example.sihua.wisdomnews.Base.GovAffairsPager;
import com.example.sihua.wisdomnews.Base.HomePager;
import com.example.sihua.wisdomnews.Base.NewsCenterPager;
import com.example.sihua.wisdomnews.Base.SettingPager;
import com.example.sihua.wisdomnews.Base.SmartServicePager;
import com.example.sihua.wisdomnews.CustomedView.NoScrollViewPager;
import com.example.sihua.wisdomnews.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * ContentFragment
 */
public class ContentFragment extends BaseFragment {

    private RadioGroup rg_function;
    private NoScrollViewPager vp_content;
    private ArrayList<BasePager> myPagerList;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        rg_function= (RadioGroup) view.findViewById(R.id.rg_functions);
        vp_content= (NoScrollViewPager) view.findViewById(R.id.vp_content);
        return view;
    }

    @Override
    public void initData() {
        myPagerList=new ArrayList<BasePager>();
        //初始化5个子页面
        /*for (int i = 0; i < 5; i++) {
            BasePager bp=new BasePager(mActivity);
            myPagerList.add(bp);
        }*/
        myPagerList.add(new HomePager(mActivity));
        myPagerList.add(new NewsCenterPager(mActivity));
        myPagerList.add(new SmartServicePager(mActivity));
        myPagerList.add(new GovAffairsPager(mActivity));
        myPagerList.add(new SettingPager(mActivity));
        vp_content.setAdapter(new MyPagerAdapter());
        rg_function.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vp_content.setCurrentItem(0);
                        break;
                    case R.id.rb_news:
                        vp_content.setCurrentItem(1);
                        break;
                    case R.id.rb_smart:
                        vp_content.setCurrentItem(2);
                        break;
                    case R.id.rb_gov:
                        vp_content.setCurrentItem(3);
                        break;
                    case R.id.rb_setting:
                        vp_content.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
        vp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                myPagerList.get(position).initData();//调用initdata来初始化特定的数据(会预加载)
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        myPagerList.get(0).initData();//手动初始化首页
    }

    /**
     * pageradapter
     */
    private class MyPagerAdapter extends PagerAdapter {

        private BasePager pager;

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
            pager = myPagerList.get(position);
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    public NewsCenterPager getNewsCenterPager(){
        return (NewsCenterPager) myPagerList.get(1);
    }
}
