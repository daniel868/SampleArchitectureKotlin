package com.example.kotlinmvvm_rxjava.data.network

import android.util.Log

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


const val TAG = "Network Request"


class HttpSuccessInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response? = null
        try {
            response = chain.proceed(request)
        } catch (exception: IOException) {
            Log.e(TAG, "intercept: ", exception)
        }
        if (!response?.isSuccessful!!) {
            throw Exception("Response null")
        }
        return response
    }

}