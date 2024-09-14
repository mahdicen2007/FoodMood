package com.mahdicen.foodmood.features.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahdicen.foodmood.ext.ImageUrl
import com.mahdicen.foodmood.ext.MyScreens
import com.mahdicen.foodmood.model.data.SearchFood
import com.mahdicen.foodmood.ui.theme.BlackDark
import com.mahdicen.foodmood.ui.theme.Orange

@Composable
fun SearchScreen(nav: NavHostController) {

    val sysUi = rememberSystemUiController()
    sysUi.setStatusBarColor(darkIcons = true, color = Color.White)
    sysUi.setNavigationBarColor(darkIcons = true, color = Color.White)

    val navigation = nav
    val viewModel = hiltViewModel<SearchViewModel>()

    val searchValue = viewModel.searchValue.observeAsState("")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            SearchBar(
                searchValue.value,
                onValueChanges = {
                    viewModel.searchValue.value = it
                    Log.v("testValue", searchValue.value)
                    if (it.isNotBlank())
                        viewModel.searchFood(it[0].toString())
                    else {
                        viewModel.searchFood.value = listOf()
                    }
                },
            )
        }

        if (viewModel.searchFood.value.isNotEmpty()) {

            items(viewModel.searchFood.value.size) { item ->

                SearchItem(searchFood = viewModel.searchFood.value[item]) {
                    navigation.navigate(MyScreens.FoodScreen.route + "/" + it)
                }

            }

        } else {

            item {
                MainAnimation()
            }

        }


    }

}

@Composable
fun SearchBar(
    searchValue: String,
    onValueChanges: (String) -> Unit,
) {
    MainTextField(
        edtValue = searchValue,
        icon = Icons.Default.Search,
        hint = "First letter of the meal...",
        onValueChanges = onValueChanges
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem(searchFood: SearchFood.Meal, onCardClicked: (String) -> Unit) {

    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 12.dp)
            .fillMaxWidth(),
        onClick = {
                  onCardClicked.invoke(searchFood.idMeal)
        },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = searchFood.strMealThumb.ifEmpty { ImageUrl + "Lime" },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .size(100.dp)
            )

            Column {

                Text(
                    text = searchFood.strMeal.ifEmpty { "" },
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = searchFood.strArea.ifEmpty { "" },
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = searchFood.strCategory.ifEmpty { "" },
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = (searchFood.strTags ?: "").toString(),
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit
) {

    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        modifier = Modifier
            .fillMaxWidth(0.88f)
            .padding(vertical = 12.dp),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        },
        shape = ShapeDefaults.Medium,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedLeadingIconColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            unfocusedTrailingIconColor = Color.Gray,
            focusedLeadingIconColor = Color.Gray,
            focusedTrailingIconColor = Color.Gray,
            focusedLabelColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            focusedSupportingTextColor = Color.Black,
            textColor = Color.Black
        ),
        maxLines = 1,

        )

}

@Composable
fun MainAnimation() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(com.mahdicen.foodmood.R.raw.search)
    )

    LottieAnimation(
        modifier = Modifier
            .size(270.dp)
            .padding(top = 32.dp, bottom = 16.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

}

