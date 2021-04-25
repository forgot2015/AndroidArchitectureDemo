package com.linzongfu.myarchitecture.mvvm

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 15:46
 * Description:
 */
class MvvmViewModel(var view: MvvmActivity) {
    private var model: MvvmModel

    init {
        model = MvvmModel()
    }

    public fun tryLogin(account: String?, password: String?) {
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