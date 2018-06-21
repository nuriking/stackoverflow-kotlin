package com.ilnur.steckoverflow_kotlin.ui.main

import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import com.ilnur.steckoverflow_kotlin.ui.IBaseView

interface IMain : IBaseView {
    fun fillLayout(answers: List<AnswerModel>)

    fun setNumberCountFavorite(count: String)

    fun hideKeyboard()

}