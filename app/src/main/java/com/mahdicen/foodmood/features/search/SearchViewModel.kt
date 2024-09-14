package com.mahdicen.foodmood.features.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdicen.foodmood.ext.NetworkChecker
import com.mahdicen.foodmood.ext.coroutineExceptionHandler
import com.mahdicen.foodmood.model.data.RandomFood
import com.mahdicen.foodmood.model.data.SearchFood
import com.mahdicen.foodmood.model.repo.main.MainRepository
import com.mahdicen.foodmood.model.repo.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val netChecker: NetworkChecker
) : ViewModel() {

    val searchFood = mutableStateOf<List<SearchFood.Meal>>(listOf())
    var searchValue = MutableLiveData("")

    fun searchFood(f :String) {

        if (netChecker.isInternetConnected) {

            viewModelScope.launch(Dispatchers.IO) {

                if (f.isBlank() && f.isBlank()) {
                    searchFood.value = listOf()
                } else {
                    searchFood.value = repository.searchFood(f).meals
                }

            }

        }

    }

}