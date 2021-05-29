package app.kato.nim.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.android.synthetic.main.activity_today_task.*

class TodayTask : AppCompatActivity() {

    var realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_task)


        val task: Task? = read()
        if (task != null) {
            displayNameText.setText(task.name)
            displayTimeText.setText(task.time)
        }







        floatingActionButton.setOnClickListener {
            val toEditTaskIntent = Intent(this, EditTask::class.java)
            startActivity(toEditTaskIntent)
        }

        backButton.setOnClickListener {
            val toMainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(toMainActivityIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    fun read(): Task? {
        return realm.where(Task::class.java).findFirst()
    }

}