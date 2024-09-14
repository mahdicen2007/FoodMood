package com.mahdicen.foodmood.model.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Filtered(
    @SerializedName("meals")
    val meals: List<Meal>
) {
    @Keep
    data class Meal(
        @SerializedName("strMeal")
        val strMeal: String,
        @SerializedName("strMealThumb")
        val strMealThumb: String,
        @SerializedName("idMeal")
        val idMeal: String
    )
}