package github.sachin2dehury.recipeapp.presentation.listing

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.recipeapp.domain.ResultType
import github.sachin2dehury.recipeapp.domain.listing.RecipeFilterUseCase
import github.sachin2dehury.recipeapp.domain.listing.RecipeListingUseCase
import github.sachin2dehury.recipeapp.domain.listing.model.FilterModel
import github.sachin2dehury.recipeapp.presentation.listing.model.ListingScreenModel
import github.sachin2dehury.recipeapp.viewModelIoScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListingViewModel @Inject constructor(
    private val recipeFilterUseCase: RecipeFilterUseCase,
    private val recipeListingUseCase: RecipeListingUseCase,
) : ViewModel() {

    private val _data = MutableStateFlow(ListingScreenModel())
    val data = _data.asStateFlow()

    private fun getFilters() = viewModelIoScope.launch {
        recipeFilterUseCase.invoke().onEach { result ->
            when (result) {
                is ResultType.Loading -> _data.update { it.copy(isLoading = true) }
                is ResultType.Success -> _data.update {
                    it.copy(
                        isLoading = false,
                        filters = result.data,
                    )
                }

                is ResultType.Error -> _data.update { it.copy(error = result.message) }
            }
        }
    }

    fun updateFilter(filterModel: FilterModel) {
        _data.update {
            it.copy(selectedFilter = filterModel.strArea)
        }
        getRecipeList()
    }

    private fun getRecipeList() = viewModelIoScope.launch {
        recipeListingUseCase.invoke(data.value.selectedFilter).onEach { result ->
            when (result) {
                is ResultType.Loading -> _data.update { it.copy(isLoading = true) }
                is ResultType.Success -> _data.update {
                    it.copy(
                        isLoading = false,
                        recipeList = result.data,
                    )
                }

                is ResultType.Error -> _data.update { it.copy(error = result.message) }
            }
        }
    }

    init {
        getFilters()
        data.value.filters?.firstOrNull()?.let {
            updateFilter(it)
        }
    }
}
