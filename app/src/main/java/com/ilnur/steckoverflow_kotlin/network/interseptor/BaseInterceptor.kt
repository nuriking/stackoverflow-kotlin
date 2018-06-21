package com.ilnur.steckoverflow_kotlin.network.interseptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

open class BaseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = beforeRequest(chain.request())
        val response = chain.proceed(request)
        return if (response.isSuccessful) {
            onResponse(response)
        } else {
            onError(response)
        }
    }

    protected open fun beforeRequest(request: Request): Request {
        return request
    }

    protected open fun onResponse(response: Response): Response {
        return response
    }

    protected open fun onError(response: Response): Response {
        return response
    }
}
