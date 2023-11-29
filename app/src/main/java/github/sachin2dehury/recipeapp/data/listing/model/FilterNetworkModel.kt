package github.sachin2dehury.recipeapp.data.listing.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilterNetworkModel(
    val meals: List<Meal?>? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Meal(
        val strArea: String? = null,
        val strCategory: String? = null,
    )
}
