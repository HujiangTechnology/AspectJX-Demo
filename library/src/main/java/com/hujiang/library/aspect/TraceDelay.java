package com.hujiang.library.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * class description here
 *
 * @author xiaoming1109@gmail.com
 * @version 1.0.0
 * @since 2018-08-21
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TraceDelay {
}
