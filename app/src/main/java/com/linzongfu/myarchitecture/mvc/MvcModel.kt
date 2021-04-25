package com.linzongfu.myarchitecture.mvc

import android.text.TextUtils

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 09:30
 * Description:
 */
class MvcModel {
    fun isAccountValid(account: String): Boolean = !TextUtils.isEmpty(account) && account.length >= 6
    fun isPasswordValid(password: String): Boolean =
        !TextUtils.isEmpty(password) && password.length >= 6
}