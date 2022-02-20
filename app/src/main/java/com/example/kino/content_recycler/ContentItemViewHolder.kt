package com.example.kino.content_recycler

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.kino.R
import com.example.kino.models.Content

class ContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.title)
    private val details = itemView.findViewById<TextView>(R.id.button)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val favorite = itemView.findViewById<ImageView>(R.id.favorite)

    fun bind(item: Content, listener: ContentItemAdapter.ContentClickListener) {
        if (item.isFavorite) {
            favorite.setColorFilter(Color.RED)
        } else {
            favorite.setColorFilter(Color.GRAY)
        }

        name.apply {
            text = item.name
        }

        description.apply {
            text = item.description
            maxLines = 2
        }

        Glide.with(image.context)
            .load(item.poster)
            .centerCrop()
            .into(image)

        image.apply {
            // setImageResource("item.poster")
        }

        details.setOnClickListener {
            listener.onClickDetails(item, adapterPosition)
        }

        favorite.setOnClickListener {
            listener.onClickFavorite(item, adapterPosition)
        }
    }
}




