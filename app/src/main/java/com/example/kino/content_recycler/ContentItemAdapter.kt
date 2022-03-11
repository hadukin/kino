package com.example.kino.content_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.R
import com.example.kino.models.Movie

class ContentItemAdapter(
    private val items: ArrayList<Movie>,
    private val listener: ContentClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContentItemViewHolder(inflater.inflate(R.layout.content_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentItemViewHolder -> {
                holder.bind(items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ContentClickListener {
        fun onClickDetails(contentItem: Movie, position: Int)
        fun onClickFavorite(contentItem: Movie, position: Int)
    }
}