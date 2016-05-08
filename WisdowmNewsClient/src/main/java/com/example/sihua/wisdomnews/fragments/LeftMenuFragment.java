package com.example.sihua.wisdomnews.fragments;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sihua.wisdomnews.Base.NewsCenterPager;
import com.example.sihua.wisdomnews.Bean.NewsData;
import com.example.sihua.wisdomnews.R;
import com.example.sihua.wisdomnews.activities.HomeActivity;

import java.util.List;

/**
 * left menu
 */
public class LeftMenuFragment extends BaseFragment {
    private ListView lv_list;
    private List<NewsData.NewsMenuData> newsmenu;
    private int mCurrentMenuItem;
    private MenuAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        lv_list= (ListView) view.findViewById(R.id.lv_list);
        return view;
    }

    @Override
    public void initData() {
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击事件
                mCurrentMenuItem=position;
                adapter.notifyDataSetChanged();
                setCurrentMenuDetalPager(position);
                toggleSlidingMenu();
            }
        });
    }

    /**
     * 切换slidingmenu状态
     */
    private void toggleSlidingMenu() {
        HomeActivity home= (HomeActivity) mActivity;
        home.getSlidingMenu().toggle();//切换状态,显示时隐藏，隐藏时显示
    }

    /**
     * 设置当前菜单详情页
     * @param position
     */
    private void setCurrentMenuDetalPager(int position) {
        HomeActivity activity=(HomeActivity)mActivity;
        ContentFragment contentFragment = activity.getContentFragment();//获取content页面fragment
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();//获取新闻中心页面
        newsCenterPager.setCurrentMenuDetalPager(position);//获取当前详情页
    }

    class MenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsmenu.size();
        }

        @Override
        public Object getItem(int position) {
            return newsmenu.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.list_menu_item, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(newsmenu.get(position).getTitle());
            if(position==mCurrentMenuItem){
                tv_title.setEnabled(true);
            }else{
                tv_title.setEnabled(false);
            }
            return view;
        }
    }

    /**
     * 设置网络数据
     * @param news
     */
    public void setMenuData(NewsData news){
        //拿取数据，展示
        newsmenu = news.getData();
        adapter = new MenuAdapter();
        lv_list.setAdapter(adapter);

    }
}
