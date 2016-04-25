@file:JvmName("Greeter")
package com.hujiang.library.demo

import android.util.Log

/**
 * class description here
 * @author simon
 * @version 1.0.0
 * @since 2016-03-04
 */

open class Greeter {

    var name = "Greeter"

    public open fun greet() {
        Log.i("helloAOP", "Hello $name")
    }
}