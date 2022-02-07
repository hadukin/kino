package com.example.kino.views.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.views.home.details.ContentDetailFragment
import com.example.kino.content_recycler.ContentItemAdapter
import com.example.kino.utils.FakeBackend
import com.example.kino.R
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.models.Content
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log


class HomeFragment : Fragment(), ContentItemAdapter.ContentClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView

    private val vm by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

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

        parentFragmentManager.setFragmentResult(FAVORITE_LIST_RESULT, Bundle().apply {
            putParcelableArrayList(FAVORITE_LIST, vm.favoriteItems.value)
        })
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }
        recycler.layoutManager = layoutManager
        recycler.adapter = ContentItemAdapter(FakeBackend.content, this)
    }

    override fun onClickDetails(contentItem: Content, position: Int) {
        childFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .add(R.id.fragment_home_id, ContentDetailFragment(contentItem))
            .addToBackStack(null)
            .commit()
    }


    override fun onClickFavorite(contentItem: Content, position: Int) {
        if (!vm.favoriteItems.value?.contains(contentItem)!!) {
            val updated = contentItem.copy(isFavorite = true)
            vm.addFavorite(updated)
            FakeBackend.content[position] = updated
            recycler.adapter?.notifyItemChanged(position)
            showSnackBar("Контент добавлен в избранное") {
                vm.removeFavorite(updated)
                FakeBackend.content[position] = contentItem
                recycler.adapter?.notifyItemChanged(position)
            }
        } else {
            vm.removeFavorite(contentItem)
            FakeBackend.content[position] = contentItem.copy(isFavorite = false)
            recycler.adapter?.notifyItemChanged(position)

            showSnackBar("Контент удален из избранного") {
                vm.addFavorite(contentItem)
                FakeBackend.content[position] = contentItem
                recycler.adapter?.notifyItemChanged(position)
            }
        }
    }

    private fun showSnackBar(text: String, onCancel: () -> Unit) {
        // Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
        //     .setAction("Отмена") {
        //         onCancel()
        //     }
        //     .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
        //     .show()
    }

    companion object {
        const val FAVORITE_LIST = "favorite_list"
        const val FAVORITE_LIST_RESULT = "favorite_list_result"
    }
}