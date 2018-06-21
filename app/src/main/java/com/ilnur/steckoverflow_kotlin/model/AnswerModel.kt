package com.ilnur.steckoverflow_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnswerModel(
        @SerializedName("view_count") val viewCount: Int,
        @SerializedName("answer_count") val answerCount: Int,
        @SerializedName("score") val scope: Int,
        @SerializedName("creation_date") val creationDate: Long,
        @SerializedName("question_id") val questionId: Int,
        @SerializedName("link") val link: String,
        @SerializedName("title") val title: String,
        @SerializedName("owner") val owner: Owner
):Serializable