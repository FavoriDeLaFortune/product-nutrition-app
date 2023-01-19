package com.example.productnutritionapp.data.network

import com.example.productnutritionapp.data.network.model.NetworkAutoCompleteRecipeList
import com.example.productnutritionapp.data.network.model.NetworkRecipeList
import com.example.productnutritionapp.data.network.model.Recipe

fun NetworkAutoCompleteRecipeList.toDomainModel() : List<String> = this.results.map { it.display }