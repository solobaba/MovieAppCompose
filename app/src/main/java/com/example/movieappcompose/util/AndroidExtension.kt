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

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SnackBarDemo() {
//    val scaffoldState: ScaffoldState = rememberScaffoldState()
//    val coroutineScope: CoroutineScope = rememberCoroutineScope()
//
//    Scaffold(scaffoldState = scaffoldState) {
//        Button(onClick = {
//            coroutineScope.launch {
//                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
//                    message = msg,
//                    actionLabel = "Do something"
//                )
//                when (snackbarResult) {
//                    SnackbarResult.Dismissed -> TODO()
//                    SnackbarResult.ActionPerformed -> TODO()
//                }
//            }
//        }) {
//            Text(text = "Click me!")
//        }
//    }
//}