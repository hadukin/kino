package com.example.kino.features.content.presentation.favorite

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.MainViewModel
import com.example.kino.R
import com.example.kino.features.content.presentation.content_recycler.ContentItemAdapter
import com.example.kino.databinding.FragmentFavoriteBinding
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.presentation.home.details.ContentDetailFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : Fragment(), ContentItemAdapter.ContentClickListener {
    // private val vm: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ContentItemAdapter

    private val vm: MainViewModel by sharedViewModel()

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
        vm.content.observe(viewLifecycleOwner, movieObserver)
    }

    private val movieObserver = Observer<ArrayList<Movie>> {
        val favoriteMovies = it.filter { it.isFavorite }
        adapter.setMovieList(favoriteMovies as ArrayList<Movie>)
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }

        adapter = ContentItemAdapter(this)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
    }

    override fun onClickDetails(contentItem: Movie, position: Int) {
        childFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_favorite_id, ContentDetailFragment(contentItem))
            .addToBackStack(null)
            .commit()
    }

    override fun onClickFavorite(contentItem: Movie, position: Int) {
        val resultText = adapter.removeFavorite(contentItem, position)
        vm.removeFavorite(contentItem)
        showSnackBar(resultText) {
            vm.addFavorite(contentItem)
            adapter.addFavorite(contentItem, position)
        }
    }

    private fun showSnackBar(text: String, onCancel: () -> Unit) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
            .setAction("Отмена") {
                onCancel()
            }
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .show()
    }

}