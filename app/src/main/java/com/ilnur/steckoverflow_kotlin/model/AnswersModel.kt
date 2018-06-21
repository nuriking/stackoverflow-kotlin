package com.ilnur.steckoverflow_kotlin.model

import com.google.gson.annotations.SerializedName

data class AnswersModel(@SerializedName("items") val items: List<AnswerModel>)
