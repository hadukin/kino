package com.example.kino

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.models.Content

class ContentItemAdapter(
    private val items: List<Content>,
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
        fun onClickImage(contentItem: Content, position: Int)
        fun onClickFavorite(contentItem: Content, position: Int)
    }
}