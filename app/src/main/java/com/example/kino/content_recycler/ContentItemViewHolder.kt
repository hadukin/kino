package com.example.kino.content_recycler

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import com.bumptech.glide.load.model.LazyHeaders
import com.example.kino.R
import com.example.kino.models.Content
// import com.example.kino.models.Playlist
import com.example.kino.models.Station

class ContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.title)
    private val details = itemView.findViewById<TextView>(R.id.button)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val favorite = itemView.findViewById<ImageView>(R.id.favorite)

    fun bind(item: Station, listener: ContentItemAdapter.ContentClickListener) {
        // if (item.isFavorite) {
        //     favorite.setColorFilter(Color.RED)
        // } else {
        //     favorite.setColorFilter(Color.GRAY)
        // }

        name.apply {
            text = item.name
        }

        description.apply {
            text = item.description
            maxLines = 2
        }

        // image.load(item.images[0].url) {
        //     headers(Headers("apikey", "Njc0NWI0MzEtZWQyOS00ZGY2LTgzYjQtM2FmOTYxNWUyMzNk")).build()
        // }

        // val request = ImageRequest.Builder(image.context)
        //     .data(item.images[0].url)
        //     // .setHeader("Cache-Control", "max-age=31536000")
        //     .target(image)
        //     .build()
        // imageLoader.execute(request)

        // val glideUrl = GlideUrl(
        //     item.images[0].url, LazyHeaders.Builder()
        //         .addHeader("apikey", "Njc0NWI0MzEtZWQyOS00ZGY2LTgzYjQtM2FmOTYxNWUyMzNk").build()
        // )
        Glide.with(image.context)
            .load(item.links?.largeImage?.href)
            .centerCrop()
            .into(image)
        // Glide.with(image.context)
        //     .load(glideUrl)
        //     .into(image)

        // Glide.with(image.context)
        //     .load("${item.images[0].url}/?apikey=Njc0NWI0MzEtZWQyOS00ZGY2LTgzYjQtM2FmOTYxNWUyMzNk")
        //     .centerCrop()
        //     .into(image)

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




