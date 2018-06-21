package com.ilnur.steckoverflow_kotlin.ui.result

import android.annotation.SuppressLint
import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.ilnur.steckoverflow_kotlin.R
import com.ilnur.steckoverflow_kotlin.app.App
import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import com.ilnur.steckoverflow_kotlin.ui.BasePresenter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class ResultPresenter : BasePresenter<IResult>() {

    @Inject
    lateinit var model: ResultModel

    private lateinit var answer: AnswerModel
    private var isExist = false

    init {
        App().getAppComponent().inject(this)
    }

    fun setAnswer(answer: AnswerModel, context: Context) {
        if (model.getFavoriteData(answer.questionId) != null) {
            isExist = true
            getView().isExist(context.resources.getString(R.string.delete_from_db))
        } else {
            isExist = false
            getView().isExist(context.resources.getString(R.string.add_to_db))
        }

        this.answer = answer
        getView().fillLayout(this.answer.title, this.answer.link,
                "Создано: ".plus(getDate(this.answer.creationDate)),
                "Автор: ".plus(this.answer.owner.displayName),
                this.answer.owner.profileImage,
                "Репутация: ".plus(this.answer.owner.reputation))
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(milliSeconds: Long?): String {
        val dateFormat = "dd.MM.yyyy"
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds!!
        return formatter.format(calendar.time)
    }

    fun clickSite() {
        if (!answer.link.isEmpty()) getView().lookSite(answer.link)
    }

    fun dbAddOrDelete() {
        if (isExist) {
            model.deleteFromDB(answer)
                    .doOnSubscribe { getView().onShowLoading() }
                    .doFinally { getView().onHideLoading() }
                    .subscribe(this::onSuccessAction, this::onError)
        } else {
            model.addToDB(answer)
                    .doOnSubscribe { getView().onShowLoading() }
                    .doFinally { getView().onHideLoading() }
                    .subscribe(this::onSuccessAction, this::onError)
        }
    }

    private fun onSuccessAction() {
        getView().onBack()
    }

    private fun onError(t: Throwable) {
        getView().onError(t.localizedMessage)
    }
}

