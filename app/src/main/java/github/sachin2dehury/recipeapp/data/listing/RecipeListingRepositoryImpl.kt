package github.sachin2dehury.recipeapp.data.listing

import github.sachin2dehury.recipeapp.data.RecipeService

class RecipeListingRepositoryImpl(private val service: RecipeService) : RecipeListingRepository {
    override suspend fun getRecipeList(
        area: String?,
        category: String?,
        ingredient: String?,
    ) = service.getRecipeList(area, ingredient, category)

    override suspend fun getRecipeFilter() = service.getFilters(LIST)

    companion object {
        const val LIST = "list"
    }
}
