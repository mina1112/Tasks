package app.kato.nim.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_today_task.*

class TodayTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_task)

        floatingActionButton.setOnClickListener {
            val toEditTaskIntent = Intent(this, EditTask::class.java)
            startActivity(toEditTaskIntent)
        }

        backButton.setOnClickListener {
            val toMainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(toMainActivityIntent)
        }
    }
}