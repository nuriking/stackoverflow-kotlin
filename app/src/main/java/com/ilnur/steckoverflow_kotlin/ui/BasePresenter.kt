package com.ilnur.steckoverflow_kotlin.ui

import com.arellomobile.mvp.MvpPresenter

open class BasePresenter<T : IBaseView> : MvpPresenter<T>() {

    protected fun getView(): T {
        return viewState
    }
}
