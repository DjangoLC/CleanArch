package com.example.cleanarchme.views

import android.util.Log
import com.example.cleanarchme.views.common.Scope
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.ref.WeakReference

abstract class BasePresenterImpl<V : BaseView>(
    override val uiDispatcher: CoroutineDispatcher
) : Scope by Scope.Impl(uiDispatcher), BasePresenter<V> {

    private var _view: WeakReference<V>? = null

    val view: V
        get() {
            return _view?.get()!!
        }


    override fun attach(view: V) {
        this._view = WeakReference(view)
        initScope()
    }

    override fun detach() {
        this._view = null
        Log.e("BasePresenterImpl", "view value:  _view: ${this._view}")
        destroyScope()
    }
}
