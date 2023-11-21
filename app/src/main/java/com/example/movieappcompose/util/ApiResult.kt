package com.example.movieappcompose.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
    data class NetworkError(val message: String = "Check your network connection") :
        ApiResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${exception.message}]"
            is NetworkError -> "Error[exception=$message]"
        }
    }
}

class NetworkStatusInterceptor constructor(private val connectionManager: ConnectionManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}

class NetworkUnavailableException(message: String = "No network available :(") :
    IOException(message)

class ConnectionManager constructor(val context: Context) {
    fun isConnected(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = manager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting == true
    }
}
