package com.linzongfu.myarchitecture.mvc

import android.text.TextUtils
import android.util.Log

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 09:30
 * Description:
 */
class MvcModel {
    val TAG = "MvcModel"

    fun getRecordList(): List<MvcRecord> {
        val list = ArrayList<MvcRecord>()
        for (index in 0..5) {
            val record = MvcRecord("MVC 3 月 $index 日", "消费了 $index 元")
            list.add(record)
        }
        Log.e(TAG, list.toString())
        return list
    }
}