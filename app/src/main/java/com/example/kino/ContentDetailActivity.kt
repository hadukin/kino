package com.example.kino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.kino.models.Content
import com.example.kino.models.DetailResult

class ContentDetailActivity : AppCompatActivity() {
    private lateinit var detailResult: DetailResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_detail)
        detailResult = DetailResult(false, "")

        val content = intent.getParcelableExtra<Content>(CONTENT) ?: error("Content is not passed")

        findViewById<ImageView>(R.id.content_detail_poster_id).apply {
            setImageResource(content.poster)
            contentDescription = content.name
        }

        findViewById<TextView>(R.id.content_detail_title_id).apply {
            text = content.name
        }

        findViewById<TextView>(R.id.content_detail_description_id).apply {
            text = content.description
        }

        findViewById<CheckBox>(R.id.content_detail_is_favorite_checkbox_id).setOnCheckedChangeListener { _, isChecked ->
            detailResult = detailResult.copy(isFavorite = isChecked)
            val intent = Intent().apply {
                putExtra(DETAIL_RESULT, detailResult)
            }
            setResult(RESULT_OK, intent)
        }

        findViewById<EditText>(R.id.content_detail_text_field_id).addTextChangedListener { value ->
            detailResult = detailResult.copy(message = value.toString())
            val intent = Intent().apply {
                putExtra(DETAIL_RESULT, detailResult)
            }
            setResult(RESULT_OK, intent)
        }
    }

    companion object {
        private const val CONTENT = "content"
        const val DETAIL_RESULT = "detail_result"
    }
}

