package com.example.movieappcompose.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.net.ConnectivityManager
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

/**
 * Shows a short Toast with a String Parameter.
 */
fun Context.shortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.shortIntToast(msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

object NetworkUtils {
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}

fun Context.showSnackBar(msg: String) {
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SnackbarDemo() {
//    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`
//    val coroutineScope = rememberCoroutineScope()
//
//    Scaffold(
//        modifier = Modifier,
//        scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
//    ) {
//        Button(
//            onClick = {
//                coroutineScope.launch { // using the `coroutineScope` to `launch` showing the snackbar
//                    // taking the `snackbarHostState` from the attached `scaffoldState`
//                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
//                        message = "This is your message",
//                        actionLabel = "Do something."
//                    )
//                    when (snackbarResult) {
//                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
//                        SnackbarResult.ActionPerformed -> Log.d("SnackbarDemo", "Snackbar's button clicked")
//                    }
//                }
//            }
//        ) {
//            Text(text = "A button that shows a Snackbar")
//        }
//    }
//}
//
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