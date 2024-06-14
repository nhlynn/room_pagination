package com.heaven.room_pagination.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.heaven.room_pagination.paging.ItemPagingSource
import com.heaven.room_pagination.persistence.dao.ItemDao
import com.heaven.room_pagination.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel
@Inject constructor(repository: ItemRepository): ViewModel(){
    val response = repository.getItem().cachedIn(viewModelScope)
}