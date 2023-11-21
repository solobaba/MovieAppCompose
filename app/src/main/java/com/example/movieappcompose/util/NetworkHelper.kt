package com.example.movieappcompose.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.reflect.KClass

object NetworkHelper {

    suspend fun <T : Any> safeApiResult(
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
        call: suspend () -> Response<T>,
    ): ApiResult<T> {
        return withContext(ioDispatcher) {
            try {
                val response = call.invoke()
                when {
                    response.isSuccessful -> ApiResult.Success(response.body()!!)

                    response.code() == 500 -> ApiResult.Error(Exception("An error has occur, Check your input"))
                    else -> {
                        //404 {"Message":"Consumer Not Found"}
                        println("Developer Error: $response")
                        ApiResult.Error(Exception("Developer Error: $response"))
                    }
                }
            } catch (e: Throwable) {
                val networkExceptions: List<KClass<out IOException>> =
                    listOf(
                        SocketTimeoutException::class,
                        ConnectException::class,
                        UnknownHostException::class
                    )
                if (e::class in networkExceptions) {
                    ApiResult.NetworkError()
                } else {
                    println("Unknown Error: $e")
                    ApiResult.Error(Exception("Something went wrong"))
                }
            }
        }
    }
}