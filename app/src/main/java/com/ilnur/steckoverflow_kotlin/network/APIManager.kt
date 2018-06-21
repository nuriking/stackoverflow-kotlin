package com.ilnur.steckoverflow_kotlin.network

import com.ilnur.steckoverflow_kotlin.model.AnswersModel
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIManager(baseUrl: String, interceptors: List<Interceptor>) {
    private var apiService: APIService

    init {
        val httpClient = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
        for (interceptor in interceptors) {
            httpClient.addInterceptor(interceptor)
        }
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request: Request = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }
        val client = httpClient.build()
        val mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        apiService = mRetrofit.create(APIService::class.java)
    }

    fun getAnswerModels(title: String): Observable<AnswersModel> {
        return apiService.getAnswerModels(title)
    }
}