package com.hujiang.library.demo

import android.app.Activity
import android.os.Bundle

/**
 * class description here
 * @author simon
 * @version 1.0.0
 * @since 2016-03-07
 */
open class ButtonActivity : Activity() {

    override open fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.button_activity_layout)

        Greeter().greet()
    }
}