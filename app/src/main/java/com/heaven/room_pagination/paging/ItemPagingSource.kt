package com.heaven.room_pagination.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.heaven.room_pagination.persistence.dao.ItemDao
import com.heaven.room_pagination.persistence.table.ItemVO
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

class ItemPagingSource
@AssistedInject constructor(@Assisted private val dao: ItemDao) :
    PagingSource<Int, ItemVO>() {
    override fun getRefreshKey(state: PagingState<Int, ItemVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemVO> {
        val page = params.key ?: 0

        return try {
            val entities = dao.getItem(params.loadSize, page * params.loadSize)

            // simulate page loading
            if (page != 0) delay(1000)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}