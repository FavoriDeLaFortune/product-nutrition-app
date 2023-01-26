package com.example.productnutritionapp.data.network.repository

import androidx.paging.PagingData
import com.example.productnutritionapp.data.network.model.Recipe
import kotlinx.coroutines.flow.Flow


interface NetworkRepository {
    suspend fun getAutoCompleteList(query: String) : Flow<List<String>>

    suspend fun getPagedRecipes(query: String) : Flow<PagingData<Recipe>>
}