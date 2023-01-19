package com.example.productnutritionapp.data.network.retrofit

import com.example.productnutritionapp.data.network.model.NetworkAutoCompleteRecipeList
import com.example.productnutritionapp.data.network.model.NetworkRecipeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/list")
    suspend fun getRecipes(
        @Query("q") query: String,
        @Query("from") from: Int,
        @Query("size") size: Int
    ): Response<NetworkRecipeList>

    @GET("recipes/auto-complete")
    suspend fun getAutoCompleteList(
        @Query("prefix") prefix: String
    ): Response<NetworkAutoCompleteRecipeList>
}