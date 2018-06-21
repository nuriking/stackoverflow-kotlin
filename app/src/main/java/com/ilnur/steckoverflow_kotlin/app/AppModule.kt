package com.ilnur.steckoverflow_kotlin.app

import com.ilnur.steckoverflow_kotlin.data.AppDataBase
import com.ilnur.steckoverflow_kotlin.data.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule(val app: App) {

    @Named("BaseUrl")
    @Provides
    fun provideBaseUrl(): String {
        return app.getUrl()
    }

    @Provides
    fun provideAppDatabase(): AppDataBase {
        return app.getAppDatabase()
    }

    @Provides
    fun providesToDoDao(): FavoriteDao {
        return app.getAppDatabase().favoriteDao()
    }
}