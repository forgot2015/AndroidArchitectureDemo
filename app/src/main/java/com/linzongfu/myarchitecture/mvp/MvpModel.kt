package com.linzongfu.myarchitecture.mvp

import android.text.TextUtils
import android.util.Log

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 10:44
 * Description:
 */
class MvpModel {
    val TAG = "MvpModel"

    fun getRecordList(): List<MvpRecord> {
        val list = ArrayList<MvpRecord>()
        for (index in 0..5) {
            val record = MvpRecord("MVP 3 月 $index 日", "消费了 $index 元")
            list.add(record)
        }
        Log.e(TAG, list.toString())
        return list
    }
}