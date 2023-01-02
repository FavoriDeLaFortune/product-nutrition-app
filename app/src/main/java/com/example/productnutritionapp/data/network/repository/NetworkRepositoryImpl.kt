package com.example.productnutritionapp.data.network.repository

import com.example.productnutritionapp.data.network.retrofit.RecipeApi
import com.example.productnutritionapp.data.network.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepositoryImpl(private val recipeApi: RecipeApi) : NetworkRepository {
    override suspend fun getAutoCompleteList(prefix: String) = withContext(Dispatchers.IO) {
        try {
            val response = recipeApi.getAutoCompleteList(prefix)
            if (response.isSuccessful) {
                response.body()!!.toDomainModel()
            } else {
                listOf()
            }
        } catch (_: java.lang.Exception) {
            listOf()
        }
    }
}