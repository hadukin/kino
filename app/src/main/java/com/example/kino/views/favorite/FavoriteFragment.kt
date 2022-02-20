package com.example.kino.views.favorite

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.R
import com.example.kino.content_recycler.ContentItemAdapter
import com.example.kino.databinding.FragmentFavoriteBinding
import com.example.kino.models.Content
// import com.example.kino.models.Playlist
import com.example.kino.models.Station
import com.example.kino.utils.FakeBackend
import com.example.kino.views.home.HomeViewModel
import com.example.kino.views.home.details.ContentDetailFragment

class FavoriteFragment : Fragment(), ContentItemAdapter.ContentClickListener {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        recycler = binding.recycler
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }
        // val adapter = ContentItemAdapter(FakeBackend.favorites, this)
        // recycler.layoutManager = layoutManager
        // recycler.adapter = adapter
    }

    override fun onClickDetails(contentItem: Station, position: Int) {
        // childFragmentManager
        //     .beginTransaction()
        //     .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        //     .replace(R.id.fragment_favorite_id, ContentDetailFragment(contentItem))
        //     .addToBackStack(null)
        //     .commit()
    }

    override fun onClickFavorite(contentItem: Station, position: Int) {
        // FakeBackend.removeFromFavorite(contentItem)
        // recycler.adapter?.notifyItemRemoved(position)
    }
}