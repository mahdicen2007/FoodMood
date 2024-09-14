package com.mahdicen.foodmood.model.net

import com.mahdicen.foodmood.model.data.Category
import com.mahdicen.foodmood.model.data.Filtered
import com.mahdicen.foodmood.model.data.Food
import com.mahdicen.foodmood.model.data.RandomFood
import com.mahdicen.foodmood.model.data.SearchFood
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/json/v1/1/random.php")
    suspend fun getRandomFood() : RandomFood

    @GET("/api/json/v1/1/lookup.php/{i}")
    suspend fun getFoodById(@Query("i") id :String) : Food

    @GET("/api/json/v1/1/search.php/{f}")
    suspend fun getSearchFood(@Query("f") f :String) : SearchFood

    @GET("/api/json/v1/1/categories.php")
    suspend fun getAllCategories() : Category

    @GET("/api/json/v1/1/filter.php/{c}")
    suspend fun getCategoryFood(@Query("c") c :String) : Filtered


}