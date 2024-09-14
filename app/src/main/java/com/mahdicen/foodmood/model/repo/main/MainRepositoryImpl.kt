package com.mahdicen.foodmood.model.repo.main

import com.mahdicen.foodmood.model.data.Food
import com.mahdicen.foodmood.model.data.RandomFood
import com.mahdicen.foodmood.model.net.ApiService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) :MainRepository {


    override suspend fun getRandomFood(): RandomFood {
        return apiService.getRandomFood()
    }

    override suspend fun getFoodById(id: String): Food {
        return apiService.getFoodById(id)
    }

}