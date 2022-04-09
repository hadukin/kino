package com.example.kino.features.content.presentation.content_recycler

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kino.R
import com.example.kino.features.content.data.models.Movie


class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar);

    fun bind() {
        progressBar
    }
}
