package com.mahdicen.foodmood.features.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdicen.foodmood.ext.NetworkChecker
import com.mahdicen.foodmood.ext.coroutineExceptionHandler
import com.mahdicen.foodmood.model.data.RandomFood
import com.mahdicen.foodmood.model.repo.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val netChecker: NetworkChecker
) : ViewModel() {

    val randomFood = mutableStateOf<List<RandomFood.Meal>>(listOf())

    init {

        if (netChecker.isInternetConnected) {


            viewModelScope.launch(Dispatchers.IO) {

                randomFood.value = repository.getRandomFood().meals
                Log.v("threadMain" , Thread.currentThread().toString())


            }

        }

    }

}