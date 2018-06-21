package com.ilnur.steckoverflow_kotlin.app

import android.arch.persistence.room.Room
import android.os.Handler
import android.os.Looper
import android.support.multidex.MultiDexApplication
import com.ilnur.steckoverflow_kotlin.Constant
import com.ilnur.steckoverflow_kotlin.data.AppDataBase
import com.ilnur.steckoverflow_kotlin.network.NetworkModule
import com.ilnur.steckoverflow_kotlin.network.interseptor.NetworkNotAvailableInterceptor
import com.ilnur.steckoverflow_kotlin.ui.BaseActivity

class App : MultiDexApplication(),NetworkNotAvailableInterceptor.IOnNetworkIsNotAvailableFireListener {
    companion object {
        private lateinit var instance: App
        private lateinit var appComponent: AppComponent
        private var dataBase: AppDataBase? = null
        private var currentActivity: BaseActivity? = null
    }

    init {
        instance = this

        getAppDatabase()

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(getUrl(), this))
                .build()
        appComponent.inject(this)
    }

    fun getAppDatabase(): AppDataBase {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(instance,
                    AppDataBase::class.java, Constant.APP_DB_NAME)
                    .allowMainThreadQueries()
                    .build()
        }
        return dataBase as AppDataBase
    }

    fun get(): App {
        return instance
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    fun setCurrentActivity(activity: BaseActivity) {
        currentActivity = activity
    }

    fun getUrl(): String {
        return Constant.API_ROOT
    }

    override fun call(e: Throwable) {
        val handler = Handler(Looper.getMainLooper()) {
            if (currentActivity != null) {
                currentActivity!!.showNetworkFailureModal()
            }
            false
        }
        val message = handler.obtainMessage()
        message.sendToTarget()
    }

}