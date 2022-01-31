package com.example.kino

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.models.Content

class ContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.title)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val favorite = itemView.findViewById<ImageView>(R.id.favorite)

    fun bind(item: Content, listener: ContentItemAdapter.ContentClickListener) {
        Log.d("IS_FAVORITE_IN_HOLDER", "${item.isFavorite}")

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

        image.apply {
            setImageResource(item.poster)
        }

        image.setOnClickListener {
            listener.onClickImage(item, adapterPosition)
        }

        favorite.setOnClickListener {
            listener.onClickFavorite(item, adapterPosition)
        }
    }
}




