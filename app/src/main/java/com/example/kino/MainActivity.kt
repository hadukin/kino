package com.example.kino

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.kino.databinding.ActivityMainBinding
import com.example.kino.models.Content
import com.example.kino.models.DetailResult

class MainActivity : AppCompatActivity() {
    private lateinit var data: ArrayList<Content>
    private var selectedContent = arrayListOf<Content>()
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = fetchContent()

        savedInstanceState.let { bundle ->
            if (bundle != null) {
                selectedContent =
                    bundle.getParcelableArrayList<Content>(SELECTED_CONTENT) as ArrayList<Content>
            }
        }

        val linearLayout: LinearLayoutCompat = binding.mainActivityLinearLayout

        for (item in data) {
            val img = ImageView(this).apply {
                setImageResource(item.poster)
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    300
                )
            }

            val button = Button(this).apply {
                text = "Детали"
            }

            button.setOnClickListener {
                val intent = Intent(this, ContentDetailActivity::class.java)
                findViewById<TextView>(item.id).setTextColor(R.color.pure_red)
                selectedContent.add(item)
                intent.apply {
                    putExtra(CONTENT, item)
                }
                showDetails.launch(intent)
            }

            val title = TextView(this).apply {
                id = item.id
                text = item.name
                textSize = 16F
                setPadding(0, 0, 0, 12)
                if (selectedContent.contains(item)) setTextColor(R.color.pure_red)
            }

            val description = TextView(this).apply {
                text = item.description
                maxLines = 2
                setPadding(0, 0, 0, 12)
            }

            linearLayout.addView(img)
            linearLayout.addView(title)
            linearLayout.addView(description)
            linearLayout.addView(button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SELECTED_CONTENT, selectedContent)
    }

    override fun onStop() {
        super.onStop()
        intent.putParcelableArrayListExtra(SELECTED_CONTENT, selectedContent)
    }

    override fun onResume() {
        super.onResume()
        intent.getParcelableArrayExtra(SELECTED_CONTENT)
    }

    private val showDetails = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val detailResult =
                result.data?.getParcelableExtra<DetailResult>(ContentDetailActivity.DETAIL_RESULT)
            detailResult.let {
                Toast.makeText(
                    this,
                    "message: ${detailResult?.message}\nisFavorite: ${detailResult?.isFavorite}",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d(ContentDetailActivity.DETAIL_RESULT, it.toString())
            }
        }
    }

    private fun fetchContent(): ArrayList<Content> {
        return arrayListOf(
            Content(
                id = R.drawable.poster_1,
                name = "Гарри Поттер",
                description = "Жизнь десятилетнего Гарри Поттера нельзя назвать сладкой: его родители умерли, едва ему исполнился год, а от дяди и тётки, взявших сироту на воспитание, достаются лишь тычки да подзатыльники. Но в одиннадцатый день рождения Гарри всё меняется. Странный гость, неожиданно появившийся на пороге, приносит письмо, из которого мальчик узнаёт, что на самом деле он чистокровный волшебник и принят в Хогвартс — школу магии. А уже через пару недель Гарри будет мчаться в поезде Хогвартс-экспресс навстречу новой жизни, где его ждут невероятные приключения, верные друзья и самое главное — ключ к разгадке тайны смерти его родителей.\nЖизнь десятилетнего Гарри Поттера нельзя назвать сладкой: его родители умерли, едва ему исполнился год, а от дяди и тётки, взявших сироту на воспитание, достаются лишь тычки да подзатыльники. Но в одиннадцатый день рождения Гарри всё меняется. Странный гость, неожиданно появившийся на пороге, приносит письмо, из которого мальчик узнаёт, что на самом деле он чистокровный волшебник и принят в Хогвартс — школу магии. А уже через пару недель Гарри будет мчаться в поезде Хогвартс-экспресс навстречу новой жизни, где его ждут невероятные приключения, верные друзья и самое главное — ключ к разгадке тайны смерти его родителей.",
                poster = R.drawable.poster_1,
            ),
            Content(
                id = R.drawable.poster_2,
                name = "Властелин колец",
                description = "Тихая деревня, где живут хоббиты. Придя на 111-й день рождения к своему старому другу Бильбо Бэггинсу, волшебник Гэндальф начинает вести разговор о кольце, которое Бильбо нашел много лет назад. Это кольцо принадлежало когда-то темному властителю Средиземья Саурону, и оно дает большую власть своему обладателю. Теперь Саурон хочет вернуть себе власть над Средиземьем. Бильбо отдает Кольцо племяннику Фродо, чтобы тот отнёс его к Роковой Горе и уничтожил.",
                poster = R.drawable.poster_2,
            ),
            Content(
                id = R.drawable.poster_3,
                name = "Омерзительная восьмерка",
                description = "США после Гражданской войны. Легендарный охотник за головами Джон Рут по кличке Вешатель конвоирует заключенную. По пути к ним прибивается еще один охотник. Снежная буря вынуждает всех троих искать укрытие в лавке на отшибе, где уже расположилась весьма пестрая компания: генерал, шериф, мексиканец, француз и ковбой… И один из них — не тот, за кого себя выдает.",
                poster = R.drawable.poster_3,
            ),
        )
    }

    companion object {
        private const val CONTENT = "content"
        private const val SELECTED_CONTENT = "selected_content"
    }
}