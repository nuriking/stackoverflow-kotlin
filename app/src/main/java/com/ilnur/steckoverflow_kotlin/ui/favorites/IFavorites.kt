package com.ilnur.steckoverflow_kotlin.ui.favorites

import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import com.ilnur.steckoverflow_kotlin.ui.IBaseView

interface IFavorites : IBaseView {
    fun fillFavorites(answers: List<AnswerModel>)
}
