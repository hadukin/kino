package com.example.kino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kino.models.Content
import java.util.UUID

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(
            Content(
                id = UUID.randomUUID(),
                name = "Гарри Поттер",
                description = "Жизнь десятилетнего Гарри Поттера нельзя назвать сладкой: его родители умерли, едва ему исполнился год, а от дяди и тётки, взявших сироту на воспитание, достаются лишь тычки да подзатыльники. Но в одиннадцатый день рождения Гарри всё меняется. Странный гость, неожиданно появившийся на пороге, приносит письмо, из которого мальчик узнаёт, что на самом деле он чистокровный волшебник и принят в Хогвартс — школу магии. А уже через пару недель Гарри будет мчаться в поезде Хогвартс-экспресс навстречу новой жизни, где его ждут невероятные приключения, верные друзья и самое главное — ключ к разгадке тайны смерти его родителей.\nЖизнь десятилетнего Гарри Поттера нельзя назвать сладкой: его родители умерли, едва ему исполнился год, а от дяди и тётки, взявших сироту на воспитание, достаются лишь тычки да подзатыльники. Но в одиннадцатый день рождения Гарри всё меняется. Странный гость, неожиданно появившийся на пороге, приносит письмо, из которого мальчик узнаёт, что на самом деле он чистокровный волшебник и принят в Хогвартс — школу магии. А уже через пару недель Гарри будет мчаться в поезде Хогвартс-экспресс навстречу новой жизни, где его ждут невероятные приключения, верные друзья и самое главное — ключ к разгадке тайны смерти его родителей.",
                poster = R.drawable.poster_1,
            ),
            Content(
                id = UUID.randomUUID(),
                name = "Властелин колец",
                description = "Тихая деревня, где живут хоббиты. Придя на 111-й день рождения к своему старому другу Бильбо Бэггинсу, волшебник Гэндальф начинает вести разговор о кольце, которое Бильбо нашел много лет назад. Это кольцо принадлежало когда-то темному властителю Средиземья Саурону, и оно дает большую власть своему обладателю. Теперь Саурон хочет вернуть себе власть над Средиземьем. Бильбо отдает Кольцо племяннику Фродо, чтобы тот отнёс его к Роковой Горе и уничтожил.",
                poster = R.drawable.poster_2,
            ),
            Content(
                id = UUID.randomUUID(),
                name = "Омерзительная восьмерка",
                description = "США после Гражданской войны. Легендарный охотник за головами Джон Рут по кличке Вешатель конвоирует заключенную. По пути к ним прибивается еще один охотник. Снежная буря вынуждает всех троих искать укрытие в лавке на отшибе, где уже расположилась весьма пестрая компания: генерал, шериф, мексиканец, француз и ковбой… И один из них — не тот, за кого себя выдает.",
                poster = R.drawable.poster_3,
            ),
        )

        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)

        val buttons = listOf<Button>(btn1, btn2, btn3)

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val intent = Intent(this, ContentDetailActivity::class.java)
                intent.apply {
                    putExtra(CONTENT, data[index])
                }
                showDetails.launch(intent)
            }
        }
    }

    private val showDetails = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {

        }
    }

    companion object {
        private const val CONTENT = "content"
    }
}