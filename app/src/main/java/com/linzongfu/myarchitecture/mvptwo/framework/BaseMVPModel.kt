package com.waisongwang.share.mvp

import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * @author zongfulin
 * Date: 2021-05-20
 * Time: 09:55
 * Description:
 */
class BaseMVPModel : IModel {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun clear() {
        compositeDisposable.clear()
    }
}