package com.example.kino.features.content.presentation.content_recycler

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.R
import com.example.kino.features.content.data.models.Movie


class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar);

    fun bind(item: Movie, listener: ContentItemAdapter.ContentClickListener) {
        progressBar.apply {}
    }
}