package com.heaven.room_pagination

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.heaven.room_pagination.adapter.ItemAdapter
import com.heaven.room_pagination.adapter.ItemLoadStateAdapter
import com.heaven.room_pagination.databinding.ActivityMainBinding
import com.heaven.room_pagination.view_model.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mAdapter: ItemAdapter

    private val mItemViewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAdapter()
        waitResponse()
    }

    private fun waitResponse() {
        lifecycleScope.launch {
            mItemViewModel.response.collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun setUpAdapter() {
        mAdapter = ItemAdapter()
        binding.apply {
            rvItem.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            rvItem.adapter = mAdapter.withLoadStateFooter(
                ItemLoadStateAdapter()
            )
        }
    }
}