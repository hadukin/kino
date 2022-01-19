package com.example.kino

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.kino.models.Content

class ContentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_detail)

        val content =
            intent.getSerializableExtra(CONTENT) as? Content ?: error("Content is not passed")

        findViewById<ImageView>(R.id.content_detail_poster_id).apply {
            setImageResource(content.poster)
        }

        findViewById<TextView>(R.id.content_detail_title_id).apply {
            text = content.name
        }

        findViewById<TextView>(R.id.content_detail_description_id).apply {
            text = content.description
        }
    }

    companion object {
        private const val CONTENT = "content"
    }
}

