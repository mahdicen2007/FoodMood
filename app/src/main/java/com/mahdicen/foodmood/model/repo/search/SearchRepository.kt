package com.mahdicen.foodmood.model.repo.search

import com.mahdicen.foodmood.model.data.SearchFood

interface SearchRepository {

    suspend fun searchFood(f :String) :SearchFood

}