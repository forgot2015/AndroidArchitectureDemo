package com.waisongwang.share.mvp

/**
 * @author zongfulin
 * Date: 2021-05-20
 * Time: 09:45
 * Description:
 */
abstract class BaseMVPPresenter<V : IView, M : IModel>(var view: V?, var model: M?) : IPresenter {

//    protected abstract fun createModel(): M

    override fun stop() {
        model?.clear()
    }

    override fun destroy() {
        view = null
        model = null
    }

}