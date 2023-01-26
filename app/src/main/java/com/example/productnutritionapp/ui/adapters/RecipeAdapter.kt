package com.example.productnutritionapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productnutritionapp.R
import com.example.productnutritionapp.data.network.model.Recipe
import com.example.productnutritionapp.databinding.ProductItemBinding

class RecipeAdapter : PagingDataAdapter<Recipe, RecipeAdapter.Holder>(RecipeDiffCallback()) {

    class Holder(
        val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val recipe = getItem(position) ?: return
        with (holder.binding)  {
            recipeName.text = recipe.title
            loadRecipePhoto(recipeIv, recipe.image)
        }
    }

    private fun loadRecipePhoto(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(R.drawable.ic_launcher_background)
                .into(imageView)
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
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}