package com.mahdicen.foodmood.model.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Recepie(

    @SerializedName("ingredient")
    val ingredient :List<Ingredient> ,
    @SerializedName("measurement")
    val measurement :List<Measurement>

) {

    @Keep
    data class Ingredient(

        @SerializedName("ingredient")
        val ingredient: String? = "" ,
//        @SerializedName("ingredient2")
//        val ingredient2: String? = "" ,
//        @SerializedName("ingredient3")
//        val ingredient3: String? = "" ,
//        @SerializedName("ingredient4")
//        val ingredient4: String? = "",
//        @SerializedName("ingredient5")
//        val ingredient5: String? = "" ,
//        @SerializedName("ingredient6")
//        val ingredient6: String? = "" ,
//        @SerializedName("ingredient7")
//        val ingredient7: String? = "",
//        @SerializedName("ingredient8")
//        val ingredient8: String? = "" ,
//        @SerializedName("ingredient9")
//        val ingredient9: String? = "" ,
//        @SerializedName("ingredient10")
//        val ingredient10: String? = "" ,
//        @SerializedName("ingredient11")
//        val ingredient11: String? = "",
//        @SerializedName("ingredient12")
//        val ingredient12: String? = "" ,
//        @SerializedName("ingredient13")
//        val ingredient13: String? = "" ,
//        @SerializedName("ingredient14")
//        val ingredient14: String? = "" ,
//        @SerializedName("ingredient15")
//        val ingredient15: String? = "" ,
//        @SerializedName("ingredient16")
//        val ingredient16: Any? = null ,
//        @SerializedName("ingredient17")
//        val ingredient17: Any? = null ,
//        @SerializedName("ingredient18")
//        val ingredient18: Any? = null,
//        @SerializedName("ingredient19")
//        val ingredient19: Any? = null,
//        @SerializedName("ingredient20")
//        val ingredient20: Any? = null ,

    )

    @Keep
    data class Measurement(
        @SerializedName("measurement")
        val measurement: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement2: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement3: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement4: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement5: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement6: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement7: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement8: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement9: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement10: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement11: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement12: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement13: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement14: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement15: String? = "" ,
//        @SerializedName("measurement1")
//        val measurement16: Any? = null ,
//        @SerializedName("measurement1")
//        val measurement17: Any? = null ,
//        @SerializedName("measurement1")
//        val measurement18: Any? = null ,
//        @SerializedName("measurement1")
//        val measurement19: Any? = null ,
//        @SerializedName("measurement1")
//        val measurement20: Any? = null ,
    )

}