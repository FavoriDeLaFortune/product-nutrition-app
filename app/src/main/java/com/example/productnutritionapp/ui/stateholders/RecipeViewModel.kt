package com.example.productnutritionapp.ui.stateholders

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.productnutritionapp.data.network.model.Recipe
import com.example.productnutritionapp.data.network.repository.NetworkRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class RecipeViewModel(
    val handle: SavedStateHandle,
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private val queryBy = MutableLiveData("")
    val recipesFlow: Flow<PagingData<Recipe>> = queryBy.asFlow()
        .debounce(500)
        .flatMapLatest { networkRepository.getPagedRecipes(it) }
        .cachedIn(viewModelScope)
    val autoCompleteFlow: Flow<List<String>> = queryBy.asFlow()
        .flatMapLatest { networkRepository.getAutoCompleteList(it) }

    fun setQueryBy(query: String) {
        if (queryBy.value == query) return
        queryBy.value = query
    }
}