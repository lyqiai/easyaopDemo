package com.yunlu.easyaopdemo

import android.util.Log
import com.river.easyaop.*

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
@Aop
class AopTest {
    @AfterThrowing(clzName = "*", methodDesc = "onCreate(*)")
    fun onCreate() {
        Log.i("TAG", "onCreate: ")
    }

    @Around(clzName = "com/yunlu/easyaopdemo/MainActivity", methodDesc = "hello(Landroid/view/View;)")
    fun hello(method: Method) {
        val now = System.currentTimeMillis()
        method.invoke()
        println("time coat: ${System.currentTimeMillis() - now}")
    }
}