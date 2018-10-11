package com.hujiang.library.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.hujiang.library.aspect.TraceDelay

@TraceDelay
open class MainActivity : Activity() {

    open override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.aop_ability).setOnClickListener { startActivity(Intent(this@MainActivity, AOPAbilityActivity::class.java)) }
        findViewById<View>(R.id.aop_activity).setOnClickListener {
            startActivity(Intent(this@MainActivity, AOPActivity::class.java)) }
        findViewById<View>(R.id.aop_fragment).setOnClickListener {
            startActivity(Intent(this@MainActivity, FragmentActivity::class.java)) }
        findViewById<View>(R.id.aop_kotlin).setOnClickListener {
            Greeter().greet() }
        findViewById<View>(R.id.aop_normal_class).setOnClickListener {
            NormalClass("normalClass").work() }

        findViewById<View>(R.id.router_btn).setOnClickListener {
            ARouter.getInstance().build("/com/AOPActivity").navigation()
        }
//        var img : ImageView = findViewById(R.id.img_t) as ImageView

        //http://cichang.hujiang.com/images/friendquan_share.png
//        ImageLoader.getInstance().displayImage("http://cichang.hujiang.com/images/friendquan_share.png", img)

    }

    @TraceDelay()
    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
