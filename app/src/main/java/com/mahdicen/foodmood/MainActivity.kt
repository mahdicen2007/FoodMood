package com.mahdicen.foodmood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahdicen.foodmood.ext.Key_Category
import com.mahdicen.foodmood.ext.Key_Food
import com.mahdicen.foodmood.ext.MyScreens
import com.mahdicen.foodmood.features.category.CategoryFoodScreen
import com.mahdicen.foodmood.features.category.CategoryScreen
import com.mahdicen.foodmood.features.food.FoodScreen
import com.mahdicen.foodmood.features.main.MainScreen
import com.mahdicen.foodmood.features.search.SearchScreen
import com.mahdicen.foodmood.ui.theme.FoodMoodTheme
import com.mahdicen.foodmood.ui.theme.Orange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodMoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    FoodMoodUi()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodMoodUi() {


    val navController = rememberNavController()
    val context = LocalContext.current
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(MyScreens.MainScreen.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(24.dp)
                        .background(
                            color = if (selected.value == Icons.Default.Home) Orange else Color.White,
                            shape = ShapeDefaults.Large
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Search
                        navController.navigate(MyScreens.SearchScreen.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(24.dp)
                        .background(
                            color = if (selected.value == Icons.Default.Search) Orange else Color.White,
                            shape = ShapeDefaults.Large
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = if (selected.value == Icons.Default.Search) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Menu
                        navController.navigate(MyScreens.CategoryScreen.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(24.dp)
                        .background(
                            color = if (selected.value == Icons.Default.Menu) Orange else Color.White,
                            shape = ShapeDefaults.Large
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = if (selected.value == Icons.Default.Menu) Color.White else Color.DarkGray
                    )
                }

            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = MyScreens.MainScreen.route,
            modifier = Modifier.padding(it)
        ) {

            composable(
                MyScreens.MainScreen.route
            ) {
                MainScreen(navController)
            }

            composable(
                MyScreens.SearchScreen.route
            ) {
                SearchScreen(navController)
            }

            composable(
                MyScreens.CategoryScreen.route
            ) {
                CategoryScreen(navController)
            }

            composable(
                MyScreens.FoodScreen.route + "/" + "{$Key_Food}",
                arguments = listOf(navArgument(name = Key_Food) {
                    type = NavType.StringType
                })
            ) {
                FoodScreen(navController, it.arguments!!.getString(Key_Food, "null"))
            }

            composable(
                MyScreens.CategoryFoodScreen.route + "/" + "{$Key_Category}",
                arguments = listOf(navArgument(name = Key_Category) {
                    type = NavType.StringType
                })
            ) {
                CategoryFoodScreen(navController, it.arguments!!.getString(Key_Category, "null"))
            }

        }
    }


}

