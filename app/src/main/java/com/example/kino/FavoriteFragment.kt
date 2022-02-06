package com.example.kino

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txt = view.findViewById<TextView>(R.id.counter_text)

        parentFragmentManager.setFragmentResultListener(
            HomeFragment.COUNTER_RESULT,
            this
        ) { _, result ->
            txt.text = "${result.getInt(HomeFragment.COUNTER)}"
        }
    }
}