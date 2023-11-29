package github.sachin2dehury.recipeapp.presentation.listing

import androidx.recyclerview.widget.DiffUtil
import github.sachin2dehury.recipeapp.domain.listing.model.ListingModel

class ListingModelDiffUtil : DiffUtil.ItemCallback<ListingModel>() {
    override fun areItemsTheSame(oldItem: ListingModel, newItem: ListingModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ListingModel, newItem: ListingModel): Boolean {
        return oldItem == newItem
    }
}
