package github.sachin2dehury.recipeapp.data.search

import github.sachin2dehury.recipeapp.data.search.model.SearchNetworkModel
import retrofit2.Response

interface RecipeSearchRepository {
    suspend fun searchRecipe(id: String): Response<SearchNetworkModel>
}
