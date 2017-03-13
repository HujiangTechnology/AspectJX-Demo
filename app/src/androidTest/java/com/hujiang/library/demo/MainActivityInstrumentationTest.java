/*
 * MainActivityInstrumentationTest      2016-11-07
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.hujiang.library.demo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestRunner;
import android.test.suitebuilder.annotation.LargeTest;

import org.aspectj.lang.JoinPoint;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-11-07
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest extends InstrumentationTestRunner {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void sayHello() {
//        onView()
        Espresso.onView(ViewMatchers.withId(R.id.aop_activity)).perform(ViewActions.click());
    }
}