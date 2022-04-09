package com.example.kino.features.content.presentation.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.kino.R

import com.example.kino.databinding.FragmentContentDetailBinding
import com.example.kino.features.content.data.models.Movie

class ContentDetailFragment() : Fragment() {
    private lateinit var binding: FragmentContentDetailBinding

    constructor(content: Movie) : this() {
        arguments = Bundle().apply {
            putParcelable(CONTENT, content)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = arguments?.getParcelable<Movie>(CONTENT)

        content?.posterUrl.let {
            if (it != null) {
                binding.poster.load(it)
            }
        }

        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            title = "${content?.nameRu}"
        }

        binding.description.apply {
            text = "${content?.nameRu}"
        }
    }

    companion object {
        private const val CONTENT = "content"
    }

}