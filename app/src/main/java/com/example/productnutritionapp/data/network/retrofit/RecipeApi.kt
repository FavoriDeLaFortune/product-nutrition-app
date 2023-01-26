package com.example.productnutritionapp.data.network.retrofit

import com.example.productnutritionapp.data.network.model.NetworkAutoCompleteRecipeItem
import com.example.productnutritionapp.data.network.model.NetworkRecipeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("number") number: Int
    ): Response<NetworkRecipeList>

    @GET("recipes/autocomplete")
    suspend fun getAutoCompleteList(
        @Query("query") query: String,
        @Query("number") number: Int = 10,
    ): Response<List<NetworkAutoCompleteRecipeItem>>
}