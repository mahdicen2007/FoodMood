package com.mahdicen.foodmood.model.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Category(
    @SerializedName("categories")
    val categories: List<Category>
) {
    @Keep
    data class Category(
        @SerializedName("idCategory")
        val idCategory: String,
        @SerializedName("strCategory")
        val strCategory: String,
        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String,
        @SerializedName("strCategoryDescription")
        val strCategoryDescription: String
    )
}