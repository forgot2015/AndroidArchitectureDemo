package com.linzongfu.myarchitecture.mvp

import android.text.TextUtils

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 10:45
 * Description:
 */
class MvpPresenter(var view: MvpActivity) {
    private var model: MvpModel

    init {
        model = MvpModel()
    }

    public fun tryLogin(account: String, password: String) {
        if (!isAccountValid(account)) {
            view.showError("账号非法")
            return
        }

        if (!isPasswordValid(password)) {
            view.showError("密码非法")
            return
        }

        view.showLoginSucceed()
    }

    private fun isAccountValid(account: String): Boolean =
        !TextUtils.isEmpty(account) && account.length >= 6

    private fun isPasswordValid(password: String): Boolean =
        !TextUtils.isEmpty(password) && password.length >= 6

    public fun loadRecyclerData() {
        view.loadRecyclerData(model.getRecordList())
    }
}