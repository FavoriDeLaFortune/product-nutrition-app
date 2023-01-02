package com.example.productnutritionapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.example.productnutritionapp.databinding.FragmentProductListBinding
import com.example.productnutritionapp.ui.stateholders.RecipeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding: FragmentProductListBinding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        dropDownMenuEditText()
    }

    private fun dropDownMenuEditText() {
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.apply {
            searchEt.threshold = 3
            searchEt.setAdapter(adapter)
            recipeViewModel.autoCompleteList.observe(viewLifecycleOwner) {
                adapter.clear()
                adapter.addAll(it)
                adapter.notifyDataSetChanged()
            }
            searchEt.addTextChangedListener { text ->
                recipeViewModel.getAutoCompleteRecipeList(text.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}