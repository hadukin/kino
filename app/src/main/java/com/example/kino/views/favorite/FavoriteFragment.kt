package com.example.kino.views.favorite

import android.content.Intent
import android.content.res.Configuration
import android.hardware.lights.Light
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.FavoriteActivity
import com.example.kino.R
import com.example.kino.content_recycler.ContentItemAdapter
import com.example.kino.databinding.FragmentContentDetailBinding
import com.example.kino.databinding.FragmentFavoriteBinding
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.models.Content
import com.example.kino.views.home.HomeFragment
import com.example.kino.views.home.HomeViewModel
import com.example.kino.views.home.details.ContentDetailFragment

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var recycler: RecyclerView
    private var favoriteList: ArrayList<Content>? = null
    private var removedItems = arrayListOf<Content>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        recycler = binding.recycler
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(FAVORITE_LIST, favoriteList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteList = savedInstanceState?.getParcelableArrayList(FAVORITE_LIST)
        favoriteList.let {
            if (it != null) {
                initRecycler(it)
            }
        }

        parentFragmentManager.setFragmentResultListener(
            HomeFragment.FAVORITE_LIST_RESULT,
            this
        ) { _, result ->
            favoriteList = savedInstanceState?.getParcelableArrayList(FAVORITE_LIST)
                ?: result.getParcelableArrayList(HomeFragment.FAVORITE_LIST)
                        ?: arrayListOf()

            favoriteList?.let {
                initRecycler(it)
            }
        }
    }

    private val clickListener = object : ContentItemAdapter.ContentClickListener {
        override fun onClickDetails(contentItem: Content, position: Int) {
            childFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.fragment_home_id, ContentDetailFragment(contentItem))
                .addToBackStack(null)
                .commit()
        }

        override fun onClickFavorite(contentItem: Content, position: Int) {
            favoriteList?.remove(contentItem)
            removedItems.add(contentItem)
            recycler.adapter?.notifyItemRemoved(position)
        }
    }

    private fun initRecycler(data: ArrayList<Content>) {
        val orientation = resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }
        recycler.layoutManager = layoutManager
        recycler.adapter = ContentItemAdapter(data, clickListener)
    }

    companion object {
        private const val FAVORITE_LIST = "favorite_list"
    }
}