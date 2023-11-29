package github.sachin2dehury.recipeapp.domain.listing

import github.sachin2dehury.recipeapp.data.listing.RecipeListingRepository
import github.sachin2dehury.recipeapp.domain.ResultType
import github.sachin2dehury.recipeapp.domain.listing.model.ListingModel
import github.sachin2dehury.recipeapp.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RecipeListingUseCase(private val repository: RecipeListingRepository) {

    suspend operator fun invoke(
        area: String? = null,
        category: String? = null,
        ingredient: String? = null,
    ) = flow {
        emit(ResultType.Loading)
        val response = repository.getRecipeList(area, category, ingredient)
        if (response.isSuccess()) {
            val mappedData = response.body()!!.meals.orEmpty().filterNotNull().map {
                ListingModel(
                    it.idMeal.orEmpty(),
                    it.strMeal.orEmpty(),
                    it.strMealThumb.orEmpty(),
                )
            }
            emit(ResultType.Success(mappedData))
        } else {
            emit(ResultType.Error("Something went wrong!!"))
        }
    }.flowOn(Dispatchers.IO)
}
