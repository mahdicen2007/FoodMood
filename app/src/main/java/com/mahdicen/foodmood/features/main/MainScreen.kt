package com.mahdicen.foodmood.features.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahdicen.foodmood.ext.MyScreens
import com.mahdicen.foodmood.model.data.RandomFood
import com.mahdicen.foodmood.ui.theme.BlackDark
import com.mahdicen.foodmood.ui.theme.FoodMoodTheme

@Composable
fun MainScreen(nav: NavHostController) {

    val sysUi = rememberSystemUiController()
    sysUi.setStatusBarColor(darkIcons = true, color = Color.White)
    sysUi.setNavigationBarColor(darkIcons = true, color = Color.White)

    val viewModel = hiltViewModel<MainViewModel>()
    Log.v("testData", viewModel.randomFood.value.toString())


    val navigation = nav

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        ToolbarFood()

            RandomFoodCard(viewModel.randomFood.value) {
                navigation.navigate(MyScreens.FoodScreen.route + "/" + it)
            }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomFoodCard(value: List<RandomFood.Meal>, onCardClicked: (String) -> Unit) {

    val context = LocalContext.current


    if (value.isNotEmpty()) {
        val random = value[0]

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(400.dp),
            onClick = {
                onCardClicked.invoke(random.idMeal)
            }
        ) {


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                AsyncImage(
                    model = random.strMealThumb,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clip(CircleShape)
                )

                Text(
                    text = random.strMeal,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = random.strInstructions,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                    maxLines = 2,
                )


            }


        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val text = "Everytime you open this app or refresh this page,\nyou'll see a random meal " +
        "and also the instruction and any other thing within to make a the meal\n" +
                "GOOD LUCK"

        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            lineHeight = 28.sp,
            color = BlackDark
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarFood() {

    TopAppBar(
        title = {
            Text(
                text = "Food Mood",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        )
    )

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FoodMoodTheme {
        Surface(
            color = Color.White, modifier = Modifier.fillMaxSize()
        ) {
        }
    }
}



