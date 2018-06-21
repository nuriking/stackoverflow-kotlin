package com.ilnur.steckoverflow_kotlin.ui.main

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.ilnur.steckoverflow_kotlin.app.App
import com.ilnur.steckoverflow_kotlin.ui.BasePresenter
import javax.inject.Inject
import com.ilnur.steckoverflow_kotlin.model.AnswersModel
import com.ilnur.steckoverflow_kotlin.data.FavoriteData

@InjectViewState
class MainPresenter : BasePresenter<IMain>() {

    @Inject
    lateinit var model: MainModel

    init {
        App().getAppComponent().inject(this)
    }

    override fun attachView(view: IMain?) {
        super.attachView(view)
        getFavorites()
    }

    private fun getFavorites() {
        model.getFavorites()
                .doOnSubscribe { getView().onShowLoading() }
                .doFinally { getView().onHideLoading() }
                .subscribe(this::onSuccessLoadFavorites, this::onErrorLoad)
    }

    private fun onSuccessLoadFavorites(list: List<FavoriteData>) {
        if (list.isNotEmpty()) {
            getView().setNumberCountFavorite("Избранные: ".plus(list.size.toString()))
        }else {
            getView().setNumberCountFavorite("Избранные")
        }
    }

    fun getAnswerModels(title: String) {
        if (!title.isEmpty()) {
            model.getAnswerModels(title)
                    .doOnSubscribe {
                        getView().hideKeyboard()
                        getView().onShowLoading()
                    }
                    .doFinally { getView().onHideLoading() }
                    .subscribe({ answer: AnswersModel -> this.onSuccessGetObjects(answer) }, this::onErrorLoad)
        } else {
            getView().onError("Введите запрос")
        }
    }

    private fun onSuccessGetObjects(answer: AnswersModel) {
        if (answer.items.isEmpty()) {
            getView().onError("Ответы по данному запросу не найдены")
        } else {
            getView().fillLayout(answer.items)
        }
    }

    private fun onErrorLoad(throwable: Throwable) {
       Log.e("ERROR_LOADING",throwable.message.toString())
    }
}