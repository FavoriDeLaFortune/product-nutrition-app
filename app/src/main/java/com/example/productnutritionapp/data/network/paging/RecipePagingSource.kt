package com.example.productnutritionapp.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productnutritionapp.data.network.model.Recipe

typealias RecipePagingLoader = suspend (pageIndex: Int, pageSize: Int) -> List<Recipe>

class RecipePagingSource(
    private val loader: RecipePagingLoader
) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val pageIndex = params.key ?: 0

        return try {
            val recipes = loader.invoke(pageIndex, params.loadSize)
            LoadResult.Page (
                data = recipes,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (recipes.size == params.loadSize) pageIndex + 1 else null
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error (
                throwable = e
            )
        }
    }
}