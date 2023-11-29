package github.sachin2dehury.recipeapp.data.details

import github.sachin2dehury.recipeapp.data.RecipeService

class RecipeDetailsRepositoryImpl(private val service: RecipeService) : RecipeDetailsRepository {
    override suspend fun getRecipeDetails(id: String) = service.getRecipeDetails(id)
}
