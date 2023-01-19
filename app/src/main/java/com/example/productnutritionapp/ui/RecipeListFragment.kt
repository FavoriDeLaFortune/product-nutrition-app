package com.example.productnutritionapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productnutritionapp.databinding.FragmentProductListBinding
import com.example.productnutritionapp.ui.adapters.RecipeAdapter
import com.example.productnutritionapp.ui.stateholders.RecipeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding: FragmentProductListBinding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModel()
    private val autoCompleteAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )
    }
    private val recipeAdapter: RecipeAdapter by lazy { RecipeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecipeList()
        observeRecipes()
    }

    override fun onResume() {
        super.onResume()
        configureDropDownMenuEditText()
    }

    private fun observeRecipes() {
        lifecycleScope.launch {
            recipeViewModel.recipesFlow.collectLatest { pagingData ->
                recipeAdapter.submitData(pagingData) }
        }
    }

    private fun configureRecipeList() {
        binding.apply {
            recipeRv.layoutManager = LinearLayoutManager(context)
            recipeRv.adapter = recipeAdapter
        }
    }

    private fun configureDropDownMenuEditText() {
        binding.apply {
            searchEt.setAdapter(autoCompleteAdapter)
            recipeViewModel.autoCompleteList.observe(viewLifecycleOwner) {
                autoCompleteAdapter.clear()
                autoCompleteAdapter.addAll(it)
                autoCompleteAdapter.notifyDataSetChanged()
            }
            searchEt.addTextChangedListener { text ->
                recipeViewModel.getAutoCompleteRecipeList(text.toString())
                recipeViewModel.setQueryBy(text.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}