package com.example.funnymemesapp.modules.home.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.funnymemesapp.modules.core.models.CommonResponse
import com.example.funnymemesapp.modules.home.models.ui.MemeModels
import com.example.funnymemesapp.modules.home.repository.HomeRepository
import java.lang.Exception

class MemesPagingSource(
    private val generalRepository: HomeRepository,
    private val isNew: Boolean,
) : PagingSource<Int, MemeModels>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, MemeModels>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MemeModels> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val memes = getMemes(isNew)
            LoadResult.Page(
                data = memes,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (memes.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    private suspend fun getMemes(isNew: Boolean): List<MemeModels> {
        val res = if (isNew){
            generalRepository.getMeme()
        } else {
            generalRepository.getStoredMeme()
        }
        return when (res) {
            is CommonResponse.Error -> {
                listOf()
            }
            is CommonResponse.Success -> {
                res.data
            }
        }
    }
}