package github.sachin2dehury.recipeapp.data

import github.sachin2dehury.recipeapp.data.details.model.DetailNetworkModel
import github.sachin2dehury.recipeapp.data.listing.model.FilterNetworkModel
import github.sachin2dehury.recipeapp.data.listing.model.ListingNetworkModel
import github.sachin2dehury.recipeapp.domain.listing.model.FilterModel
import github.sachin2dehury.recipeapp.domain.listing.model.ListingModel
import github.sachin2dehury.recipeapp.data.search.model.SearchNetworkModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("api/json/v1/1/filter.php")
    suspend fun getRecipeList(
        @Query("a") area: String? = null,
        @Query("i") ingredient: String? = null,
        @Query("c") category: String? = null,
    ): Response<ListingNetworkModel>

    @GET("api/json/v1/1/list.php")
    suspend fun getFilters(
        @Query("a") area: String? = null,
        @Query("c") category: String? = null,
        @Query("i") ingredient: String? = null,
    ): Response<FilterNetworkModel>

    @GET("api/json/v1/1/search.php")
    suspend fun searchRecipe(
        @Query("s") query: String,
    ): Response<SearchNetworkModel>

    @GET("api/json/v1/1/lookup.php")
    suspend fun getRecipeDetails(
        @Query("i") id: String,
    ): Response<DetailNetworkModel>

    companion object {
        const val BASE_URL = "www.themealdb.com"
    }
}
