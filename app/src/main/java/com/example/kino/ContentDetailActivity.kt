package com.example.kino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.kino.databinding.ActivityContentDetailBinding
import com.example.kino.models.Content
import com.example.kino.models.DetailResult

class ContentDetailActivity : AppCompatActivity() {
    private lateinit var detailResult: DetailResult
    private lateinit var binding: ActivityContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailResult = DetailResult(false, "")

        val content = intent.getParcelableExtra<Content>(CONTENT) ?: error("Content is not passed")

        binding.contentDetailPosterId.apply {
            setImageResource(content.poster)
            contentDescription = content.name
        }

        binding.contentDetailTitleId.apply {
            text = content.name
        }

        binding.contentDetailDescriptionId.apply {
            text = content.description
        }

        binding.contentDetailIsFavoriteCheckboxId.setOnCheckedChangeListener { _, isChecked ->
            detailResult = detailResult.copy(isFavorite = isChecked)
            val intent = Intent().apply {
                putExtra(DETAIL_RESULT, detailResult)
            }
            setResult(RESULT_OK, intent)
        }

        binding.contentDetailTextFieldId.addTextChangedListener { value ->
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

