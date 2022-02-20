package com.example.kino.views.home.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bumptech.glide.Glide
import com.example.kino.R

import com.example.kino.databinding.FragmentContentDetailBinding
import com.example.kino.models.Content
import com.example.kino.views.home.HomeViewModel

class ContentDetailFragment() : Fragment() {
    private lateinit var binding: FragmentContentDetailBinding

    constructor(content: Content) : this() {
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
        val content = arguments?.getParcelable<Content>(CONTENT)

        content?.poster.let {
            if (it != null) {
                binding.poster.load(it)
            }
        }

        binding.description.text = content?.description

        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            title = "${content?.name}"
        }

        binding.description.apply {
            text = "${content?.description}"
        }
    }

    companion object {
        private const val CONTENT = "content"
    }

}