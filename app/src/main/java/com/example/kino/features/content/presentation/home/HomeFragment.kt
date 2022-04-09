package com.example.kino.features.content.presentation.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.MainViewModel
import com.example.kino.features.content.presentation.home.details.ContentDetailFragment
import com.example.kino.features.content.presentation.content_recycler.ContentItemAdapter
import com.example.kino.R
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.features.content.data.models.Movie
import com.example.kino.utils.NetworkConnection
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment(),
    ContentItemAdapter.ContentClickListener,
    NetworkConnection.NetworkServiceListener {

    // private val vm by viewModel<MainViewModel>()
    // private val vm: MainViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: MainViewModel by sharedViewModel()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ContentItemAdapter
    var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        recycler = binding.recycler

        initRecycler()
        vm.content.observe(viewLifecycleOwner, movieObserver)
        vm.isLoading.observe(viewLifecycleOwner, isLoadingObserver)

        return binding.root
    }

    private val movieObserver = Observer<ArrayList<Movie>> {
        adapter.setMovieList(it)
    }

    private val isLoadingObserver = Observer<Boolean> {
        isLoading = it
        binding.progressIndicator.isVisible = it
    }

    override fun onPause() {
        vm.content.removeObserver(movieObserver)
        vm.isLoading.removeObserver(isLoadingObserver)
        super.onPause()
    }

    override fun onResume() {
        vm.content.observe(viewLifecycleOwner, movieObserver)
        vm.isLoading.observe(viewLifecycleOwner, isLoadingObserver)
        super.onResume()
    }

    override fun onDestroy() {
        vm.content.removeObserver(movieObserver)
        vm.isLoading.removeObserver(isLoadingObserver)
        super.onDestroy()
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
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    vm.nextPage()
                    CoroutineScope(Dispatchers.IO).launch { vm.loadMore(vm.page.value ?: 1) }
                }
            }
        })
    }

    override fun onClickDetails(contentItem: Movie, position: Int) {
        childFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .add(R.id.fragment_home_id, ContentDetailFragment(contentItem))
            .addToBackStack(null)
            .commit()
    }


    override fun onClickFavorite(contentItem: Movie, position: Int) {
        val result = adapter.toggleFavorite(position)
        vm.toggleFavorite(contentItem)
        showSnackBar(result) {
            vm.toggleFavorite(contentItem)
            adapter.toggleFavorite(position)
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

    override fun onChangeNetworkStatus(status: Boolean) {
        Log.d("NETWORK_STATUS", "$status")
    }
}