package com.example.kino

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.content_recycler.ContentItemAdapter
import com.example.kino.databinding.ActivityMainBinding
import com.example.kino.models.Content
import com.example.kino.utils.FakeBackend


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: RecyclerView
    private lateinit var context: Context
    private var favoriteList = arrayListOf<Content>()

    companion object {
        private const val FAVORITE_CONTENT = "favorite_content"
        const val CONTENT = "content"
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        recycler = binding.recycler
        setContentView(binding.root)

        savedInstanceState.let { bundle ->
            if (bundle != null) {
                favoriteList =
                    bundle.getParcelableArrayList<Content>(FAVORITE_CONTENT) as ArrayList<Content>
            }
        }

        initRecycler()

        binding.buttonFavoriteActivityId?.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            intent.apply {
                putParcelableArrayListExtra(CONTENT, favoriteList)
            }
            showFavoriteActivity.launch(intent)
        }
    }

    private val clickListener = object : ContentItemAdapter.ContentClickListener {
        override fun onClickDetails(contentItem: Content, position: Int) {}

        override fun onClickFavorite(contentItem: Content, position: Int) {
            val updateItem = contentItem.copy(isFavorite = !contentItem.isFavorite)

            if (!favoriteList.contains(contentItem)) {
                favoriteList.add(updateItem)
            } else {
                favoriteList.remove(contentItem)
            }

            FakeBackend.content[position] = updateItem
            recycler.adapter?.notifyItemChanged(position)
        }
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 3)
        } else {
            LinearLayoutManager(this)
        }
        recycler.layoutManager = layoutManager
        recycler.adapter = ContentItemAdapter(FakeBackend.content, clickListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(FAVORITE_CONTENT, favoriteList)
    }

    override fun onStop() {
        super.onStop()
        intent.putParcelableArrayListExtra(FAVORITE_CONTENT, favoriteList)
    }

    override fun onResume() {
        super.onResume()
        intent.getParcelableArrayExtra(FAVORITE_CONTENT)
    }

    private val showFavoriteActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data =
                    result.data?.getParcelableArrayListExtra<Content>(FavoriteActivity.FAVORITE_VIEW_RESULT)
                data.let {
                    if (it?.isNotEmpty() == true) {
                        for (item in it) {
                            val index = FakeBackend.content.indexOf(item)
                            FakeBackend.content[index] = item.copy(isFavorite = false)
                            favoriteList.remove(item)
                            recycler.adapter?.notifyItemChanged(index)
                        }
                    }
                }
            }
        }
}