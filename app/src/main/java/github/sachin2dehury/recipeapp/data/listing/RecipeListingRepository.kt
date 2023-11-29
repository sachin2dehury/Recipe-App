package github.sachin2dehury.recipeapp.data.listing

import github.sachin2dehury.recipeapp.data.listing.model.FilterNetworkModel
import github.sachin2dehury.recipeapp.data.listing.model.ListingNetworkModel
import retrofit2.Response

interface RecipeListingRepository {
    suspend fun getRecipeList(
        area: String?,
        category: String?,
        ingredient: String?,
    ): Response<ListingNetworkModel>

    suspend fun getRecipeFilter(): Response<FilterNetworkModel>
}
