package com.example.kino.views.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.App
import com.example.kino.MainViewModel
import com.example.kino.views.home.details.ContentDetailFragment
import com.example.kino.content_recycler.ContentItemAdapter
import com.example.kino.R
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.features.content.data.datasource.ContentRemoteDataSourceImpl
import com.example.kino.features.content.data.repository.ContentRepositoryImpl
import com.example.kino.models.*
import com.example.kino.utils.NetworkConnectionChecker
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.coroutineScope
import com.example.kino.features.content.domain.repository.ContentRepository
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import kotlinx.coroutines.*


class HomeFragment : Fragment(),
    ContentItemAdapter.ContentClickListener,
    NetworkConnectionChecker.NetworkServiceListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView
    private val vm: MainViewModel by activityViewModels()
    private lateinit var adapter: ContentItemAdapter
    // private val vm: HomeViewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }

    private val contentRepository: ContentRepository by lazy {
        ContentRepositoryImpl(
            context = requireActivity().applicationContext,
            remote = ContentRemoteDataSourceImpl(App.instance.movieClient)
        )
    }

    private val getMoviePopularUseCase by lazy { GetMoviePopularUseCase(contentRepository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.recycler

        initRecycler()

        if (vm.contentList.value?.isEmpty() == true) {
            viewLifecycleOwner.lifecycle.coroutineScope.launch {
                fetchPlaylist(vm.page.value ?: 1)
                val result = getMoviePopularUseCase.execute(1, App.API_KEY)
                result.let {
                    vm.contentList.value?.addAll(it)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }
        adapter = ContentItemAdapter(vm.contentList.value!!, vm, this)

        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    // vm.nextPage()
                    // fetchPlaylist(vm.page.value ?: 1)
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
        val result = adapter.toggleFavorite(contentItem, position)
        showSnackBar(result) {
            adapter.toggleFavorite(contentItem, position)
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

    private fun fetchPlaylist(page: Int) {
        // App.instance.contentApi.getMoviePopular(page, App.API_KEY)
        //     .enqueue(object : Callback<MoviesResponse> {
        //         override fun onResponse(
        //             call: Call<MoviesResponse>,
        //             response: Response<MoviesResponse>
        //         ) {
        //             response.body().let {
        //                 if (it != null) {
        //                     vm.contentList.value?.addAll(it.results)
        //                     adapter?.notifyDataSetChanged()
        //                 }
        //             }
        //         }
        //
        //         override fun onFailure(call: Call<MoviesResponse>?, t: Throwable) {
        //             // Log.d("RESULT", "ERROR: ${t}")
        //         }
        //     })
    }

    override fun onChangeNetworkStatus(status: Boolean) {
        Log.d("&&&", "$status")
    }
}