package com.example.productnutritionapp.data.network

import com.example.productnutritionapp.data.network.model.NetworkAutoCompleteRecipeList

fun NetworkAutoCompleteRecipeList.toDomainModel() : List<String> = this.results.map { it.display }