package com.mahdicen.foodmood.model.repo.category

import com.mahdicen.foodmood.model.data.Category
import com.mahdicen.foodmood.model.data.Filtered
import java.util.logging.Filter

interface CategoryRepository {
    suspend fun getAllCategories() :Category
    suspend fun getCategoryFood(c :String) :Filtered
}