package com.example.cleanarchme.views

import com.example.cleanarchme.views.common.Scope
import kotlinx.coroutines.CoroutineDispatcher

abstract class BasePresenterImpl<V: BaseView>(
    override val uiDispatcher: CoroutineDispatcher) : Scope by Scope.Impl(uiDispatcher),BasePresenter<V> {

    var view: V? = null

    override fun attach(view: V) {
        this.view = view
        initScope()
    }

    override fun detach() {
        this.view = null
        destroyScope()
    }
}
