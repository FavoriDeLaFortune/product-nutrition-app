package com.example.productnutritionapp.ui.stateholders

import androidx.lifecycle.*
import com.example.productnutritionapp.data.network.repository.NetworkRepository
import kotlinx.coroutines.launch

class RecipeViewModel(
    val handle: SavedStateHandle,
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private var _autoCompleteList = MutableLiveData<List<String>>()
    val autoCompleteList: LiveData<List<String>> = _autoCompleteList

    fun getAutoCompleteRecipeList(query: String) {
        viewModelScope.launch {
            _autoCompleteList.value = networkRepository.getAutoCompleteList(query)
        }
    }
}