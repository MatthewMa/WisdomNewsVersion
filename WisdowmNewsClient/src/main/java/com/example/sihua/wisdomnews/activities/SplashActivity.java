package com.example.sihua.wisdomnews.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.sihua.wisdomnews.R;
import com.example.sihua.wisdomnews.global.Constants;
import com.example.sihua.wisdomnews.utils.SharedPreferenceUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    @ViewInject(R.id.btn_start)
    private Button btn_start;
    @ViewInject(R.id.rl_root)
    private RelativeLayout rl_root;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            enterHome();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();

    }

    private void initListener() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                enterHome();
            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
        com.lidroid.xutils.ViewUtils.inject(this);
        AnimationSet set=new AnimationSet(true);
        //rotate animation
        RotateAnimation rotate=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        //scale animation
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
        set.addAnimation(rotate);
        set.addAnimation(scaleAnimation);
        set.setDuration(2000);
        set.setFillAfter(true);
        rl_root.startAnimation(set);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final AnimationSet asset = new AnimationSet(true);
                TranslateAnimation trans = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 150,
                        TranslateAnimation.RELATIVE_TO_SELF, 0);//translation animation
                asset.addAnimation(trans);
                //Alpha transmation
                AlphaAnimation alpha = new AlphaAnimation(0, 1);
                asset.addAnimation(alpha);
                asset.setDuration(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_start.setVisibility(View.VISIBLE);
                        btn_start.startAnimation(asset);
                    }
                });
            }
        }, 2000);
        handler.sendEmptyMessageDelayed(0,7000);

    }

    private void enterHome() {
        if(!SharedPreferenceUtils.getSharedPreferenceBoolean(this, Constants.boolInfo,false)) {
            startActivity(new Intent(this, GuideActivity.class));
            finish();
        }else{
            //enter to Home Page
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
    }
}
