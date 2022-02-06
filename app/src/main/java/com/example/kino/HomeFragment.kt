package com.example.kino

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.models.Content


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView
    private var favoriteList = arrayListOf<Content>()

    private var count = 0

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
    }

    private val clickListener = object : ContentItemAdapter.ContentClickListener {
        override fun onClickImage(contentItem: Content, position: Int) {}

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
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }
        recycler.layoutManager = layoutManager
        recycler.adapter = ContentItemAdapter(FakeBackend.content, clickListener)
    }

    companion object {
        const val COUNTER = "counter"
        const val COUNTER_RESULT = "counter_result"
    }
}