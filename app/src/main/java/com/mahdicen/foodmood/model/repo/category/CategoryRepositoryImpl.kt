package com.mahdicen.foodmood.model.repo.category

import com.mahdicen.foodmood.model.data.Category
import com.mahdicen.foodmood.model.data.Filtered
import com.mahdicen.foodmood.model.net.ApiService

class CategoryRepositoryImpl(
    private val apiService: ApiService
) :CategoryRepository {
    override suspend fun getAllCategories(): Category {
        return apiService.getAllCategories()
    }

    override suspend fun getCategoryFood(c: String): Filtered {
        return apiService.getCategoryFood(c)
    }

}