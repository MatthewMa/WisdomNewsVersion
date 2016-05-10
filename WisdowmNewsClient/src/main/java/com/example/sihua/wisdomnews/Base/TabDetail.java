package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sihua.wisdomnews.Bean.NewsData;
import com.example.sihua.wisdomnews.Bean.TabData;
import com.example.sihua.wisdomnews.R;
import com.example.sihua.wisdomnews.global.Constants;
import com.example.sihua.wisdomnews.utils.Tools;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Sihua on 2016/5/7.
 */
public class TabDetail extends BaseMenuDetailPage {

    private NewsData.NewsMenuData.NewsTabData dataInfo;
    private ViewPager vp_hot;
    private RelativeLayout rl_hint;
    private ListView lv_news;
    private TabData data;
    private List<TabData.DataBean.TopnewsBean> topnews;
    private TextView tv_title;
    public TabDetail(Activity activity) {
        super(activity);
    }

    public TabDetail(Activity mActivity, NewsData.NewsMenuData.NewsTabData newsTabData) {
        super(mActivity);
        this.dataInfo=newsTabData;
    }

    @Override
    public View initView() {
        View view=View.inflate(mActivity, R.layout.tab_detail_pager,null);
        vp_hot= (ViewPager) view.findViewById(R.id.vp_hot);
        rl_hint= (RelativeLayout) view.findViewById(R.id.rl_hint);
        lv_news= (ListView) view.findViewById(R.id.lv_news);
        tv_title= (TextView) view.findViewById(R.id.tv_title);
        vp_hot.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title=topnews.get(position).getTitle();
                tv_title.setText(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    @Override
    public void initData() {
        //展示数据
        getDataFromServer();
    }

    private void getDataFromServer() {
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.SERVER_URL + dataInfo.getUrl(), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                parseData(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(TabDetail.this.mActivity,s,Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private void parseData(String result) {
        Gson gson=new Gson();
        data = gson.fromJson(result, TabData.class);
        topnews = data.getData().getTopnews();
        vp_hot.setAdapter(new TopNewsAdapter());
        tv_title.setText(topnews.get(0).getTitle());
    }

    class TopNewsAdapter extends PagerAdapter{

        private BitmapUtils utils;

        public TopNewsAdapter(){
            utils = new BitmapUtils(mActivity);
            utils.configDefaultLoadingImage(R.mipmap.topnews_item_default);//设置默认等待图片
        }

        @Override
        public int getCount() {
            return topnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //创建imageview
            ImageView iv=new ImageView(mActivity);
            String url= Tools.changeUrl(topnews.get(position).getTopimage());
            //对imageview进行处理
            //iv.setBackgroundResource(R.mipmap.topnews_item_default);
            utils.display(iv,url);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
