package com.example.kino

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kino.models.Content

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
            HomeFragment.FAVORITE_LIST_RESULT,
            this
        ) { _, result ->
            val favoriteList = result.getParcelableArrayList<Content>(HomeFragment.FAVORITE_LIST)

            Log.d("FAVVVV", "${favoriteList}")

            txt.text = "${favoriteList?.size}"
        }
    }

}