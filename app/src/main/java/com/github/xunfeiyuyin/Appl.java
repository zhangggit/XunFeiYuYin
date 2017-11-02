package com.github.xunfeiyuyin;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by zhanggang on 2017/11/2.
 */

public class Appl extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=59fa8cf0");
    }
}
