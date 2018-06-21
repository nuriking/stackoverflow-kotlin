package com.ilnur.steckoverflow_kotlin.network

import com.ilnur.steckoverflow_kotlin.model.AnswersModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("2.2/search?site=stackoverflow")
    fun getAnswerModels(@Query(value = "intitle") intitle: String): Observable<AnswersModel>
}