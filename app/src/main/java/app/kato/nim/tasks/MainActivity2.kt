package app.kato.nim.tasks

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))
        realm = Realm.getDefaultInstance()
        list.layoutManager = LinearLayoutManager(this)
        val schedules = realm.where<Schedule>().findAll()
        val adapter = ScheduleAdapter(schedules)
        list.adapter = adapter


        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder() // Realmの設定を定義
                .schemaVersion(1L) // 新しいスキーマのバージョンを設定
                .migration(MyMigration()) // マイグレーション用のコードを設定
                .build()
        Realm.setDefaultConfiguration(realmConfig) // 上記の設定をRealmにセット

        val memo: Memo? = read()

        adapter.setOnItemClickListener { id ->
            val intent = Intent(this, ScheduleEditActivity::class.java)
                .putExtra("schedule_id", id)
            startActivity(intent)
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        fab.setOnClickListener { view ->
            val intent = Intent(this, ScheduleEditActivity::class.java)
            startActivity(intent)
        }

        topButton.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            val str = editText.text.toString()
            intent.putExtra(EXTRA_MESSAGE, str)
        }

        if (memo !=null) {
            editText.setText(memo.task)
        }

        topButton.setOnClickListener {
            val task: String = editText.text.toString()
            save(task)
        }

        }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun read(): Memo? {
        return realm.where(Memo::class.java).findFirst()
    }

    fun save(task: String) {
        val memo: Memo? = read()
        realm.executeTransaction {
            if (memo != null) {
                memo.task = task
            } else {
                val newMemo: Memo = it.createObject(Memo::class.java)
                newMemo.task = task
            }
        }
    }
}