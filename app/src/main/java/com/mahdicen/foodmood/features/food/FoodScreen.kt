package com.mahdicen.foodmood.features.food

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahdicen.foodmood.ext.ExpandableText
import com.mahdicen.foodmood.ext.ImageUrl
import com.mahdicen.foodmood.model.data.Food
import com.mahdicen.foodmood.model.data.Recepie
import com.mahdicen.foodmood.ui.theme.FoodMoodTheme
import com.mahdicen.foodmood.ui.theme.Orange_lime

@Composable
fun FoodScreen(navController: NavHostController, foodId: String) {


    val context = LocalContext.current
    val nav = navController

    val sysUi = rememberSystemUiController()
    sysUi.setStatusBarColor(darkIcons = true, color = Color.Transparent)
    sysUi.setNavigationBarColor(darkIcons = true, color = Color.White)

    val viewModel = hiltViewModel<FoodViewModel>()
    viewModel.getfood(foodId)


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (viewModel.food.value.meals.isNotEmpty()) {

            BackgroundCover(viewModel.food.value.meals[0])

            SurfaceDetail(viewModel.food.value.meals[0])

        }


    }


}

@Composable
fun SurfaceDetail(meal: Food.Meal) {

    val ingredient = listOf(
        Recepie.Ingredient(meal.strIngredient1),
        Recepie.Ingredient(meal.strIngredient2),
        Recepie.Ingredient(meal.strIngredient3),
        Recepie.Ingredient(meal.strIngredient4),
        Recepie.Ingredient(meal.strIngredient5),
        Recepie.Ingredient(meal.strIngredient6),
        Recepie.Ingredient(meal.strIngredient7),
        Recepie.Ingredient(meal.strIngredient8),
        Recepie.Ingredient(meal.strIngredient9),
        Recepie.Ingredient(meal.strIngredient10),
        Recepie.Ingredient(meal.strIngredient11),
        Recepie.Ingredient(meal.strIngredient12),
        Recepie.Ingredient(meal.strIngredient13),
        Recepie.Ingredient(meal.strIngredient14),
        Recepie.Ingredient(meal.strIngredient15),
        Recepie.Ingredient((meal.strIngredient16 ?: "").toString()),
        Recepie.Ingredient((meal.strIngredient17 ?: "").toString()),
        Recepie.Ingredient((meal.strIngredient18 ?: "").toString()),
        Recepie.Ingredient((meal.strIngredient19 ?: "").toString()),
        Recepie.Ingredient((meal.strIngredient20 ?: "").toString()),
    )
    val measurement = listOf(
        Recepie.Measurement(meal.strMeasure1),
        Recepie.Measurement(meal.strMeasure2),
        Recepie.Measurement(meal.strMeasure3),
        Recepie.Measurement(meal.strMeasure4),
        Recepie.Measurement(meal.strMeasure5),
        Recepie.Measurement(meal.strMeasure6),
        Recepie.Measurement(meal.strMeasure7),
        Recepie.Measurement(meal.strMeasure8),
        Recepie.Measurement(meal.strMeasure9),
        Recepie.Measurement(meal.strMeasure10),
        Recepie.Measurement(meal.strMeasure11),
        Recepie.Measurement(meal.strMeasure12),
        Recepie.Measurement(meal.strMeasure13),
        Recepie.Measurement(meal.strMeasure14),
        Recepie.Measurement(meal.strMeasure15),
        Recepie.Measurement((meal.strMeasure16 ?: "").toString()),
        Recepie.Measurement((meal.strMeasure17 ?: "").toString()),
        Recepie.Measurement((meal.strMeasure18 ?: "").toString()),
        Recepie.Measurement((meal.strMeasure19 ?: "").toString()),
        Recepie.Measurement((meal.strMeasure20 ?: "").toString()),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        item {

            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 80.dp)
                    .clip(CircleShape)
            )

            Text(
                text = meal.strMeal,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            ExpandableText(text = meal.strInstructions, fontSize = 18.sp)

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {

                Text(
                    text = "Ingredients",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

            }
        }

        val count = ingredientsBarCounter(meal = meal , ingredient)


        items(count) {
            IngredientItem(ingredient = ingredient[it] , measurement = measurement[it] )
        }

    }

}



fun ingredientsBarCounter(
    meal: Food.Meal,
    ingredient: List<Recepie.Ingredient>,
) :Int {

    var count = 0
    for (i in ingredient.indices) {
        if (ingredient[i].ingredient == "") {
            break
        } else {
            count += 1
        }
    }

    return count

}

@Composable
fun IngredientItem(ingredient: Recepie.Ingredient, measurement: Recepie.Measurement) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = ImageUrl + "${ingredient.ingredient}-Small.png",
                contentDescription = null,
                modifier = Modifier.padding(vertical = 4.dp).background(Color.Transparent),
            )

            Text(
                text = ingredient.ingredient!!,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
                color = Color.Black
            )

            Text(
                text = measurement.measurement!!,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.Black
            )

        }

    }

}

@Composable
fun BackgroundCover(meal: Food.Meal) {

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
    ) {


        Box(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            GradientBackground()

        }

    }

}

@Composable
fun GradientBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    // use Brush.verticalGradeint to change the angle
                    colors = listOf(
                        Color.Transparent, // Start color
                        Color.White // End color
                    ),
                )
            )
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    FoodMoodTheme {
        Surface(
            color = Color.White, modifier = Modifier.fillMaxSize()
        ) {
            IngredientItem(
                ingredient = Recepie.Ingredient("Butter"),
                measurement = Recepie.Measurement("2")
            )
        }
    }
}
