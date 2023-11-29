package github.sachin2dehury.recipeapp.presentation.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import github.sachin2dehury.recipeapp.databinding.ListItemRecipeBinding

class RecipeListAdapter : RecyclerView.Adapter<RecipeListItemViewHolder>() {
    private val diffUtil = ListingModelDiffUtil()
    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListItemViewHolder {
        val binding =
            ListItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeListItemViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecipeListItemViewHolder, position: Int) {
        val item = differ.currentList[position] ?: return
        holder.binding.tvName.text = item.strMeal
        holder.binding.ivRecipe.load(item.strMealThumb) {
            transformations(RoundedCornersTransformation(20f))
            crossfade(true)
            crossfade(500)
        }
    }
}
