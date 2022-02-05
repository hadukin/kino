package com.example.kino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.databinding.ActivityFavoriteBinding
import com.example.kino.models.Content

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteList: ArrayList<Content>
    private lateinit var recycler: RecyclerView
    private var removedItems = arrayListOf<Content>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recycler = binding.recycler

        // favoriteList = intent.getParcelableArrayListExtra(MainActivity.CONTENT)
        //     ?: error("Content is not passed")

        initRecycler(favoriteList)
    }

    private val clickListener = object : ContentItemAdapter.ContentClickListener {
        override fun onClickImage(contentItem: Content, position: Int) {}

        override fun onClickFavorite(contentItem: Content, position: Int) {
            favoriteList.remove(contentItem)
            removedItems.add(contentItem)
            recycler.adapter?.notifyItemRemoved(position)

            val intent = Intent().apply {
                putExtra(FAVORITE_VIEW_RESULT, removedItems)
            }
            setResult(RESULT_OK, intent)
        }
    }

    private fun initRecycler(data: ArrayList<Content>) {
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        recycler.adapter = ContentItemAdapter(data, clickListener)
    }

    companion object {
        const val FAVORITE_VIEW_RESULT = "favorite_view_result"
    }
}