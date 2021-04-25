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
}