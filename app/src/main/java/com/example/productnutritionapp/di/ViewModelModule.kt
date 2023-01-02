package com.example.productnutritionapp.di

import com.example.productnutritionapp.ui.stateholders.RecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RecipeViewModel(get(), get())}
}