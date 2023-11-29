package com.example.movieappcompose.util

import android.util.Log
import com.google.gson.Gson

fun logTrace(text: String) =  Log.d("Smartx", text)

//Json To Object
fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

//Object To Json String
fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}

fun <A> A.toJsonJson(): String? {
    return Gson().toJson(this)
}