package com.mahdicen.foodmood.ext

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

const val KEY_VIEWMODEL = "viewModel"
const val jj = "/api/json/v1"
const val BaseUrl = "https://www.themealdb.com"
const val Key_Food = "foodId"
const val Key_Category = "Category"
const val ImageUrl = "https://www.themealdb.com/images/ingredients/"

val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    Log.v("error" , throwable.message.toString())
}
