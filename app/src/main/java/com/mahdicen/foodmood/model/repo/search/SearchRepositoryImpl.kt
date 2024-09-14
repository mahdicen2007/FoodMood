package com.mahdicen.foodmood.model.repo.search

import com.mahdicen.foodmood.model.data.SearchFood
import com.mahdicen.foodmood.model.net.ApiService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) :SearchRepository {

    override suspend fun searchFood(f :String): SearchFood {
        return apiService.getSearchFood(f)
    }

}