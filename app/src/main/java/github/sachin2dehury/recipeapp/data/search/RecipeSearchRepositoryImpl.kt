package github.sachin2dehury.recipeapp.data.search

import github.sachin2dehury.recipeapp.data.RecipeService

class RecipeSearchRepositoryImpl(private val service: RecipeService) : RecipeSearchRepository {
    override suspend fun searchRecipe(id: String) = service.searchRecipe(id)
}
