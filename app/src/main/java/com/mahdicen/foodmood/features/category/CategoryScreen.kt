package com.mahdicen.foodmood.features.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahdicen.foodmood.ext.MyScreens
import com.mahdicen.foodmood.model.data.Category
import com.mahdicen.foodmood.ui.theme.BlackDark

@Composable
fun CategoryScreen(nav: NavHostController) {

    val sysUi = rememberSystemUiController()
    sysUi.setStatusBarColor(darkIcons = true, color = Color.White)
    sysUi.setNavigationBarColor(darkIcons = true, color = Color.White)

    val navigation = nav
    val viewModel = hiltViewModel<CategoryViewModel>()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {

            ToolbarCategory()

        }

        items(viewModel.categories.value.size) {item ->

            if (viewModel.categories.value.isNotEmpty()) {
                CategoryItem(category = viewModel.categories.value[item]) {
                    nav.navigate(MyScreens.CategoryFoodScreen.route + "/" + it)
                }
            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarCategory() {

    TopAppBar(
        title = {
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black ,
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(category :Category.Category , onCardClicked :(String) -> Unit) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            onCardClicked.invoke(category.strCategory)
        }
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            AsyncImage(
                model = category.strCategoryThumb,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(CircleShape)
            )

            Text(
                text = category.strCategory,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
                fontWeight = FontWeight.Medium
            )

            Text(
                text = category.strCategoryDescription,
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp , bottom = 12.dp),
                maxLines = 8
            )


        }


    }

}

