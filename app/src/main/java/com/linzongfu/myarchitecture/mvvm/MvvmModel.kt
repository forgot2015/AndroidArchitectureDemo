package com.linzongfu.myarchitecture.mvvm

import android.text.TextUtils

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 15:45
 * Description:
 */
class MvvmModel {
    fun isAccountValid(account: String?): Boolean =
        !TextUtils.isEmpty(account) && account!!.length >= 6

    fun isPasswordValid(password: String?): Boolean =
        !TextUtils.isEmpty(password) && password!!.length >= 6

    fun getRecordList(): List<MvvmRecord> {
        val list = ArrayList<MvvmRecord>()
        for (index in 0..5) {
            val record = MvvmRecord("MVVM 3 月 $index 日", "消费了 $index 元")
            list.add(record)
        }
        return list
    }
}