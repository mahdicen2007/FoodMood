package com.mahdicen.foodmood.model.repo.main

import com.mahdicen.foodmood.model.data.Food
import com.mahdicen.foodmood.model.data.RandomFood

interface MainRepository {

    suspend fun getRandomFood() : RandomFood
    suspend fun getFoodById(id :String) : Food

}