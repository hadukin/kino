package com.example.kino.content_recycler

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kino.R
import com.example.kino.models.Movie
import com.example.kino.models.posterUrl

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar);

    fun bind(item: Movie, listener: ContentItemAdapter.ContentClickListener) {
        progressBar.apply {}
    }
}