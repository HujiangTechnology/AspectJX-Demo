/*
 * DemoApplication      2016-03-04
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */

package com.hujiang.library.demo;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hujiang.library.LibrarySDK;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

//import android.support.multidex.MultiDex;
//import android.support.multidex.MultiDex;
//import com.hujiang.account.AccountRunTime;
//import com.hujiang.framework.app.RunTimeManager;
//import com.hujiang.social.sdk.SocialSDK;

//import com.taobao.android.dexposed.DexposedBridge
//import com.taobao.android.dexposed.XC_MethodHook

/**
 * class description here

 * @author simon
 * *
 * @version 1.0.0
 * *
 * @since 2016-03-04
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LibrarySDK.init();

        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this).build());

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}