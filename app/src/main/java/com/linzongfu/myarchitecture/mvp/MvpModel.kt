package com.linzongfu.myarchitecture.mvp

import android.text.TextUtils

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 10:44
 * Description:
 */
class MvpModel {
    fun isAccountValid(account: String): Boolean = !TextUtils.isEmpty(account) && account.length >= 6
    fun isPasswordValid(password: String): Boolean =
        !TextUtils.isEmpty(password) && password.length >= 6
}