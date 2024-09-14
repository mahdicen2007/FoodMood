package com.mahdicen.foodmood.features.category

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdicen.foodmood.ext.NetworkChecker
import com.mahdicen.foodmood.ext.coroutineExceptionHandler
import com.mahdicen.foodmood.model.data.Category
import com.mahdicen.foodmood.model.data.Filtered
import com.mahdicen.foodmood.model.data.RandomFood
import com.mahdicen.foodmood.model.repo.category.CategoryRepository
import com.mahdicen.foodmood.model.repo.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Filter
import javax.inject.Inject

@HiltViewModel
class CategoryFoodViewModel @Inject constructor(
    private val repository: CategoryRepository,
    private val netChecker: NetworkChecker
) : ViewModel() {

    val filtered = mutableStateOf<List<Filtered.Meal>>(listOf())

    fun getCategoryFood(c :String) {

        if (netChecker.isInternetConnected) {


            viewModelScope.launch(Dispatchers.IO) {

                filtered.value = repository.getCategoryFood(c).meals

            }

        }

    }

}