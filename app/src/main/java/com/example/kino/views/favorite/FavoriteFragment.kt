package com.example.kino.views.favorite

import android.content.Intent
import android.hardware.lights.Light
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.FavoriteActivity
import com.example.kino.R
import com.example.kino.content_recycler.ContentItemAdapter
import com.example.kino.databinding.FragmentContentDetailBinding
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.models.Content
import com.example.kino.views.home.HomeFragment

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recycler: RecyclerView
    private var favoriteList: ArrayList<Content>? = null
    private var removedItems = arrayListOf<Content>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        recycler = binding.recycler
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteList = arrayListOf()

        val textView = view.findViewById<TextView>(R.id.counter_text)
        parentFragmentManager.setFragmentResultListener(
            HomeFragment.FAVORITE_LIST_RESULT,
            this
        ) { _, result ->
            favoriteList = result.getParcelableArrayList(HomeFragment.FAVORITE_LIST)
            if (favoriteList != null) {
                initRecycler(favoriteList!!)
            }
        }
    }

    private val clickListener = object : ContentItemAdapter.ContentClickListener {
        override fun onClickDetails(contentItem: Content, position: Int) {}

        override fun onClickFavorite(contentItem: Content, position: Int) {
            favoriteList?.remove(contentItem)
            removedItems.add(contentItem)
            recycler.adapter?.notifyItemRemoved(position)

            val intent = Intent().apply {
                putExtra(FavoriteActivity.FAVORITE_VIEW_RESULT, removedItems)
            }
            // setResult(AppCompatActivity.RESULT_OK, intent)
        }
    }

    private fun initRecycler(data: ArrayList<Content>) {
        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager
        recycler.adapter = ContentItemAdapter(data, clickListener)
    }

}