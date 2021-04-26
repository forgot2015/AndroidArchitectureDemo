package com.linzongfu.myarchitecture.mvvm

import android.text.TextUtils

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 15:46
 * Description:
 */
class MvvmViewModel(var view: MvvmActivity) {
    private var model: MvvmModel = MvvmModel()

    fun tryLogin(account: String?, password: String?) {
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

    private fun isAccountValid(account: String?): Boolean =
        !TextUtils.isEmpty(account) && account!!.length >= 6

    private fun isPasswordValid(password: String?): Boolean =
        !TextUtils.isEmpty(password) && password!!.length >= 6

    fun loadRecyclerData() {
        view.loadRecyclerData(model.getRecordList())
    }
}