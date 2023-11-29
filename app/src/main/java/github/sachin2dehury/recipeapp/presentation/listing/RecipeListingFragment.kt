package github.sachin2dehury.recipeapp.presentation.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.recipeapp.databinding.FragmentRecipeListingBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeListingFragment : Fragment() {

    private var binding: FragmentRecipeListingBinding? = null

    private val viewModel by viewModels<RecipeListingViewModel>()

    private val adapter = RecipeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecipeListingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        collectData()
    }

    private fun collectData() = lifecycleScope.launch {
        viewModel.data.collectLatest {
            adapter.differ.submitList(it.recipeList)
            binding?.loader?.isVisible = it.isLoading
            if (it.selectedFilter.isNullOrEmpty() && !it.filters.isNullOrEmpty()) {
                viewModel.updateFilter(it.filters.first())
            }
            it.error?.let { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupUi() = binding?.run {
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
