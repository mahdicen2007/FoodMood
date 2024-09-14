package com.mahdicen.foodmood.features.food

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdicen.foodmood.ext.NetworkChecker
import com.mahdicen.foodmood.model.data.Food
import com.mahdicen.foodmood.model.repo.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: MainRepository,
    private val netChecker: NetworkChecker
) : ViewModel() {

    val food = mutableStateOf(Food(listOf()))

    fun getfood (id :String) {

        if (netChecker.isInternetConnected) {

            viewModelScope.launch(Dispatchers.IO) {

                food.value = repository.getFoodById(id)
            }

        }

    }

}