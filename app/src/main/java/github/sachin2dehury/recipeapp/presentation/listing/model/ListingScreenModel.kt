package github.sachin2dehury.recipeapp.presentation.listing.model

import github.sachin2dehury.recipeapp.domain.listing.model.FilterModel
import github.sachin2dehury.recipeapp.domain.listing.model.ListingModel

data class ListingScreenModel(
    val isLoading: Boolean = false,
    val filters: List<FilterModel>? = null,
    val selectedFilter: String? = null,
    val recipeList: List<ListingModel>? = null,
    val error: String? = null
)