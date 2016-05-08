package com.example.sihua.wisdomnews.Base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sihua.wisdomnews.Bean.NewsData;
import com.example.sihua.wisdomnews.activities.HomeActivity;
import com.example.sihua.wisdomnews.fragments.LeftMenuFragment;
import com.example.sihua.wisdomnews.global.Constants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻实现
 */
public class NewsCenterPager extends BasePager {

    private NewsData news;

    public NewsCenterPager(Activity activity) {
        super(activity);
    }
    private List<BaseMenuDetailPage> menulist;
    @Override
    public void initData() {
        setSlidingMenuEnable(true);
        getDataFromServer();
    }

    private void getDataFromServer() {
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Constants.CATEGORY_URL, new
                RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo responseInfo) {
                        //访问成功
                        String result = (String) responseInfo.result;
                        //System.out.println(result);
                        //解析result
                        parseData(result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        //访问失败
                        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
    }

    private void parseData(String result) {
        Gson gson=new Gson();
        news = gson.fromJson(result, NewsData.class);
        //System.out.println(news);
        HomeActivity activity=(HomeActivity)mActivity;
        LeftMenuFragment leftMenuFragment = activity.getLeftMenuFragment();
        //刷新侧边栏数据
        leftMenuFragment.setMenuData(news);
        //准备4个菜单详情页
        menulist=new ArrayList<BaseMenuDetailPage>();
        menulist.add(new NewsMenuDetailPage(mActivity));
        menulist.add(new TopicMenuDetailPage(mActivity));
        menulist.add(new PicturesMenuDetailPage(mActivity));
        menulist.add(new InteractionMenuDetailPage(mActivity));
        setCurrentMenuDetalPager(0);//设置新闻为默认当前页
    }

    /**
     * 设置当前菜单详情页
     */
    public void setCurrentMenuDetalPager(int position){
        BaseMenuDetailPage detailPage = menulist.get(position);
        fl_content.removeAllViews();
        fl_content.addView(detailPage.mRootView);//将布局文件设置给framelayout
        tv_title.setText(news.getData().get(position).getTitle());//设置当前页的标题
    }


}
