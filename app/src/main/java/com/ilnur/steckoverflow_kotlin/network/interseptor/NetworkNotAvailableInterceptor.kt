package com.ilnur.steckoverflow_kotlin.network.interseptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import retrofit2.HttpException

class NetworkNotAvailableInterceptor() : BaseInterceptor() {
    private lateinit var callback: IOnNetworkIsNotAvailableFireListener

    companion object {
        val networkNotAvailableCodeList = Arrays.asList<Int>()
    }

    constructor(callback: IOnNetworkIsNotAvailableFireListener) : this() {
        this.callback = callback
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            super.intercept(chain)
        } catch (e: IOException) {
            if (e.message!!.isNotEmpty()) {
                onError(e)
            }
            throw e
        }
    }

    @Suppress("SENSELESS_COMPARISON")
    private fun onError(e: Throwable) {
        if (callback != null) {
            callback.call(e)
        }
    }

    override fun onError(response: Response): Response {
        if (networkNotAvailableCodeList.contains(response.code())) {
            onError(HttpException(retrofit2.Response.error<Any>(response.code(), response.body())))
        }
        return response
    }

    interface IOnNetworkIsNotAvailableFireListener {
        fun call(e: Throwable)
    }
}
