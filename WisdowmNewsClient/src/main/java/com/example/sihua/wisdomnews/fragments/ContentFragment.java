package com.example.sihua.wisdomnews.fragments;

import android.view.View;
import android.widget.RadioGroup;

import com.example.sihua.wisdomnews.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * ContentFragment
 */
public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.rg_functions)
    private RadioGroup rg_function;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        ViewUtils.inject(this.getActivity());
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
