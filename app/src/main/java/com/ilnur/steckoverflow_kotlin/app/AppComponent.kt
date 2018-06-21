package com.ilnur.steckoverflow_kotlin.app

import com.ilnur.steckoverflow_kotlin.network.NetworkModule
import com.ilnur.steckoverflow_kotlin.ui.favorites.FavoritesPresenter
import com.ilnur.steckoverflow_kotlin.ui.main.MainPresenter
import com.ilnur.steckoverflow_kotlin.ui.result.ResultPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (AppModule::class)])

interface AppComponent {
    fun inject(app: App)

    fun inject(mainPresenter: MainPresenter)

    fun inject(favoritesPresenter: FavoritesPresenter)

    fun inject(resultPresenter: ResultPresenter)
}