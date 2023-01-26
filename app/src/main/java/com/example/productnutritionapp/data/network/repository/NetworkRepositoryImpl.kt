package com.example.productnutritionapp.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.productnutritionapp.data.network.model.Recipe
import com.example.productnutritionapp.data.network.paging.RecipePagingLoader
import com.example.productnutritionapp.data.network.paging.RecipePagingSource
import com.example.productnutritionapp.data.network.retrofit.RecipeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class NetworkRepositoryImpl(private val recipeApi: RecipeApi) : NetworkRepository {
    override suspend fun getAutoCompleteList(query: String) = withContext(Dispatchers.IO) {
        flow { try {
            val response = recipeApi.getAutoCompleteList(query)
            if (response.isSuccessful) {
                emit(response.body()!!.map { it.title })
            } else {
                emit(listOf())
            }
        } catch (_: java.lang.Exception) {
            emit(listOf())
        }}
    }

    private suspend fun getRecipes(query: String, pageIndex: Int, pageSize: Int): List<Recipe> {
        return try {
            if (query == "") {
                return listOf()
            }
            val offset = pageIndex * pageSize
            val response = recipeApi.getRecipes(query, offset, pageSize)
            if (response.isSuccessful) {
                response.body()!!.results
            } else {
                listOf()
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun getPagedRecipes(query: String): Flow<PagingData<Recipe>> =
        withContext(Dispatchers.IO) {
            val loader: RecipePagingLoader = { pageIndex, pageSize ->
                getRecipes(query, pageIndex, pageSize)
            }
            return@withContext Pager(
                config = PagingConfig(
                    pageSize = PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = PAGE_SIZE
                ),
                pagingSourceFactory = { RecipePagingSource(loader) }
            ).flow
        }

    private companion object {
        const val PAGE_SIZE = 20
    }
}