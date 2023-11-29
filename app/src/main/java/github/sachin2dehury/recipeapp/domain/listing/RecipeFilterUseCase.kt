package github.sachin2dehury.recipeapp.domain.listing

import github.sachin2dehury.recipeapp.data.listing.RecipeListingRepository
import github.sachin2dehury.recipeapp.domain.ResultType
import github.sachin2dehury.recipeapp.domain.listing.model.FilterModel
import github.sachin2dehury.recipeapp.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RecipeFilterUseCase(private val repository: RecipeListingRepository) {

    suspend operator fun invoke(): Flow<ResultType<List<FilterModel>>> = flow {
        emit(ResultType.Loading)
        val response = repository.getRecipeFilter()
        if (response.isSuccess()) {
            val mappedData = response.body()!!.meals.orEmpty().filterNotNull()
                .map { FilterModel(it.strArea.orEmpty(), it.strCategory.orEmpty()) }
            emit(ResultType.Success(mappedData))
        } else {
            emit(ResultType.Error("Something went wrong!!"))
        }
    }.flowOn(Dispatchers.IO)
}
