package com.mahdicen.foodmood.ext

sealed class MyScreens(val route :String) {

    object MainScreen :MyScreens("mainScreen")
    object SearchScreen :MyScreens("searchScreen")
    object CategoryScreen :MyScreens("categoryScreen")
    object CategoryFoodScreen :MyScreens("categoryFoodScreen")
    object FoodScreen :MyScreens("foodScreen")

}
