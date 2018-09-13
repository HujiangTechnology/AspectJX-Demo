/*
 * ViewClickAspect      2016-03-04
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */

package com.hujiang.library.aspect;

import android.util.Log;
import android.widget.Button;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-03-04
 */
@Aspect
public class ViewClickAspect {


    final static String TAG = ViewClickAspect.class.getSimpleName();
    final static String onclickExpression = "execution(void **onClick**(..))";

    @After(onclickExpression)
    public void afterOnClickMethod(JoinPoint joinPoint) throws Throwable {
        Log.i(TAG, "afterOnClickMethod:::" + joinPoint.getSignature());
        printButtonText(joinPoint);
    }

    @Before(onclickExpression)
    public void beforeOnClickMethod(JoinPoint joinPoint) throws Throwable {
        Log.i(TAG, "beforeOnClickMethod:::" + joinPoint.getSignature());
        printButtonText(joinPoint);
    }

    private void printButtonText(JoinPoint joinPoint){
        if(joinPoint != null && joinPoint.getArgs() != null &&
           joinPoint.getArgs().length == 1 && joinPoint.getArgs()[0] instanceof Button) {
            Log.i(TAG, "ButtonText:::" + ((Button) joinPoint.getArgs()[0]).getText());
        }
    }
}