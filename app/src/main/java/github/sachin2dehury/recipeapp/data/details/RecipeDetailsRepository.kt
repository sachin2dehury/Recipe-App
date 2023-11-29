package github.sachin2dehury.recipeapp.data.details

import github.sachin2dehury.recipeapp.data.details.model.DetailNetworkModel
import retrofit2.Response

interface RecipeDetailsRepository {
    suspend fun getRecipeDetails(id: String): Response<DetailNetworkModel>
}
