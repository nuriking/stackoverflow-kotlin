package com.ilnur.steckoverflow_kotlin.ui.favorites

import com.arellomobile.mvp.InjectViewState
import com.ilnur.steckoverflow_kotlin.app.App
import com.ilnur.steckoverflow_kotlin.data.FavoriteData
import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import com.ilnur.steckoverflow_kotlin.model.Owner
import com.ilnur.steckoverflow_kotlin.ui.BasePresenter
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter : BasePresenter<IFavorites>() {

    @Inject
    lateinit var model: FavoritesModel

    init {
        App().getAppComponent().inject(this)
    }

    override fun attachView(view: IFavorites?) {
        super.attachView(view)
        getFavorites()
    }

    private fun getFavorites() {
        model.getFavorites()
                .doOnSubscribe { getView().onShowLoading() }
                .doFinally { getView().onHideLoading() }
                .subscribe(this::onSuccessLoadFavorites, this::onError)
    }

    private fun onSuccessLoadFavorites(list: List<FavoriteData>) {
        val answers = ArrayList<AnswerModel>()
        for (item: FavoriteData in list) {
            val owner = Owner(
                    item.reputation,
                    item.user_id,
                    item.profile_image,
                    item.display_name
            )
            answers.add(AnswerModel(
                    item.viewCount,
                    item.answerCount,
                    item.scope,
                    item.creationDate,
                    item.questionId,
                    item.link,
                    item.title,
                    owner
            )
            )
        }
        if (answers.isEmpty()) {
            getView().onError("У меня нет любимых ответов")
        }
            getView().fillFavorites(answers)
    }

    private fun onError(t: Throwable) {
        getView().onError(t.localizedMessage)
    }
}