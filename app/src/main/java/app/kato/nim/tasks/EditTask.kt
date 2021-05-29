package app.kato.nim.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.android.synthetic.main.activity_today_task.*

class EditTask : AppCompatActivity() {

    var realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

//        taskという変数に取得したデータを代入
        val task: Task? = read()

//        データベースから取得したTaskをEditTextのテキストに表示する処理
         if (task != null) {
            nameEditText.setText(task.name)
            timeEditText.setText(task.time)
        }

//        保存ボタンを押したときnameEditTextとtimeEditTextに入力されたテキストを取得save()メソッドに渡す
        saveButton.setOnClickListener {

            val name:String = nameEditText.text.toString()
            val time: String = timeEditText.text.toString()
            save(name, time)

            val toTodayTaskIntent = Intent(this, TodayTask::class.java)
            startActivity(toTodayTaskIntent)
                }
            }



    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

//    保存したタスクを取得する
//    findFirst()でデータ1つを取得　findAll()でリストを取得
    fun read(): Task? {
    return realm.where(Task::class.java).findFirst()
}

//    nameとtimeを受け取り保存する
    fun save(name: String, time: String) {
    //すでに保存されているタスクを取得　すでに保存されたデータがあればそのデータを更新する　無ければ作る
    val task: Task? = read()
//　　データベースへの書き込みを可能にする
    realm.executeTransaction {
        if (task !=null) {
            //タスクの更新
            task.name = name
            task.time = time
        } else {
            //タスクの新規作成
            val newTask : Task = it.createObject(Task::class.java)
            newTask.name = name
            newTask.time = time
        }
        /*
//スナックバー作る
        Snackbar.make(container, "保存しました！", Snackbar.LENGTH_SHORT).show() */
    }
}
}
