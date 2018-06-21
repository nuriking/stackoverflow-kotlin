package com.ilnur.steckoverflow_kotlin.ui.favorites

import com.ilnur.steckoverflow_kotlin.data.AppDataBase
import com.ilnur.steckoverflow_kotlin.data.FavoriteData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesModel @Inject constructor(var appDataBase: AppDataBase) {

    fun getFavorites(): Observable<List<FavoriteData>> {
        return Observable.create<List<FavoriteData>> { emitter ->
            emitter.onNext(appDataBase.favoriteDao().getAll())
            emitter.onComplete()
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}