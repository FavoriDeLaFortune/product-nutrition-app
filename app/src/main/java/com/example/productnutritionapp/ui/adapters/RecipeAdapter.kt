package com.example.productnutritionapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.productnutritionapp.data.network.model.Recipe
import com.example.productnutritionapp.databinding.ProductItemBinding

class RecipeAdapter : PagingDataAdapter<Recipe, RecipeAdapter.Holder>(RecipeDiffCallback()) {

    class Holder(
        val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val recipe = getItem(position) ?: return
        with (holder.binding)  {
            recipeName.text = recipe.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}