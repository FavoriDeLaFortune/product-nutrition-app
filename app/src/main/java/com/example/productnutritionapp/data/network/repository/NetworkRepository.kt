package com.example.productnutritionapp.data.network.repository

interface NetworkRepository {
    suspend fun getAutoCompleteList(query: String) : List<String>
}