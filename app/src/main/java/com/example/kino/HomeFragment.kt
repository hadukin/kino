package com.example.kino

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
import com.example.kino.databinding.FragmentHomeBinding
import com.example.kino.models.Content
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment(), ContentItemAdapter.ContentClickListener {
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
        parentFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_container, ContentDetailFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onClickFavorite(contentItem: Content, position: Int) {
        if (!favoriteList.contains(contentItem)) {
            val updated = contentItem.copy(isFavorite = true)
            favoriteList.add(updated)
            FakeBackend.content[position] = updated
            recycler.adapter?.notifyItemChanged(position)

            showSnackBar("Контент успешно добавлен в избранное") {
                favoriteList.remove(contentItem)
                FakeBackend.content[position] = contentItem
                recycler.adapter?.notifyItemChanged(position)
            }
        } else {
            favoriteList.remove(contentItem)
            FakeBackend.content[position] = contentItem.copy(isFavorite = false)
            recycler.adapter?.notifyItemChanged(position)

            showSnackBar("Контент успешно удален из избранного") {
                favoriteList.add(contentItem)
                FakeBackend.content[position] = contentItem
                recycler.adapter?.notifyItemChanged(position)
            }
        }
    }

    private fun showSnackBar(text: String, onCancel: () -> Unit) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG)
            .setAction("Отмена") {
                onCancel()
            }
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .show()
    }

    companion object {
        const val COUNTER = "counter"
        const val COUNTER_RESULT = "counter_result"
    }
}