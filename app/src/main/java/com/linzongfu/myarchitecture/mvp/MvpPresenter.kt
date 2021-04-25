package com.linzongfu.myarchitecture.mvp

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
        if (!model.isAccountValid(account)) {
            view.showError("账号非法")
            return
        }

        if (!model.isPasswordValid(password)) {
            view.showError("密码非法")
            return
        }

        view.showLoginSucceed()
    }
}