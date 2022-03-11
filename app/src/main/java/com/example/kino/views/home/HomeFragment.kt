package com.example.kino.views.home

import android.content.res.Configuration
import android.hardware.lights.Light
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.App
import com.example.kino.views.home.details.ContentDetailFragment
import com.example.kino.content_recycler.ContentItemAdapter
import com.example.kino.utils.FakeBackend
import com.example.kino.R
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.models.*
import retrofit2.Callback
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Response


class HomeFragment : Fragment(), ContentItemAdapter.ContentClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView
    private val vm: HomeViewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }

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
        fetchPlaylist()
        // fetchContent(vm.page.value ?: 1)
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }

        recycler.layoutManager = layoutManager
        recycler.adapter = ContentItemAdapter(vm.contentList.value!!, this)
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    vm.nextPage()
                    // fetchContent(vm.page.value ?: 1)
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
        // if (!FakeBackend.favorites.contains(contentItem)) {
        //     FakeBackend.addToFavorite(contentItem)
        //     recycler.adapter?.notifyItemChanged(position)
        //     showSnackBar("Контент добавлен в избранное") {
        //         FakeBackend.removeFromFavorite(contentItem)
        //         recycler.adapter?.notifyItemChanged(position)
        //     }
        //
        // } else {
        //     FakeBackend.removeFromFavorite(contentItem)
        //     recycler.adapter?.notifyItemChanged(position)
        //
        //     showSnackBar("Контент удален из избранного") {
        //         FakeBackend.addToFavorite(contentItem)
        //         recycler.adapter?.notifyItemChanged(position)
        //     }
        // }
    }

    private fun showSnackBar(text: String, onCancel: () -> Unit) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
            .setAction("Отмена") {
                onCancel()
            }
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .show()
    }

    private fun fetchPlaylist() {
        App.instance.contentApi.getMoviePopular(1, "6cd5ff50f548e8ae4e99db6d336a460b").enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body().let {
                    if (it != null) {
                        vm.contentList.value?.addAll(it.results)
                        recycler.adapter?.notifyDataSetChanged()
                    }
                    // vm.contentList.value?.addAll(response.body()!!)
                    // recycler.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable) {
                // Log.d("RESULT", "ERROR: ${t}")
            }
        })
    }

    private fun fetchContent(page: Int) {
        App.instance.contentApi.getContent(page, 10).enqueue(object : Callback<List<Content>?> {
            override fun onResponse(
                call: Call<List<Content>?>,
                response: Response<List<Content>?>
            ) {
                response.body().let {
                    // vm.contentList.value?.addAll(response.body()!!)
                    recycler.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Content>?>, t: Throwable) {
                // Log.d("RESULT", "ERROR: ${t}")
            }
        })
    }
}