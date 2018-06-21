package com.ilnur.steckoverflow_kotlin.ui.main

import com.ilnur.steckoverflow_kotlin.data.AppDataBase
import com.ilnur.steckoverflow_kotlin.data.FavoriteData
import com.ilnur.steckoverflow_kotlin.model.AnswersModel
import com.ilnur.steckoverflow_kotlin.network.APIManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainModel @Inject constructor(var apiManager: APIManager, var appDataBase: AppDataBase) {

    fun getAnswerModels(title: String): Observable<AnswersModel> {
        return this.apiManager.getAnswerModels(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getFavorites(): Observable<List<FavoriteData>> {
        return Observable.create<List<FavoriteData>> { emitter ->
            emitter.onNext(appDataBase.favoriteDao().getAll())
            emitter.onComplete()
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}