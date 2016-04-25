/*
 * NormalClass      2016-03-28
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.library.demo;

import android.util.Log;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-03-28
 */
public class NormalClass {

    private String name;

    public NormalClass(String name) {
        this.name = name;
    }

    public void work() {
        Log.i("helloAOP", name + "is working");
    }
}