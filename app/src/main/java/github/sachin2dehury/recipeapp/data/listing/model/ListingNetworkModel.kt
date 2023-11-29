package github.sachin2dehury.recipeapp.data.listing.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingNetworkModel(
    val meals: List<Meal?>? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Meal(
        val idMeal: String? = null,
        val strMeal: String? = null,
        val strMealThumb: String? = null,
    )
}
