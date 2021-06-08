package app.kato.nim.tasks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()



        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder() // Realmの設定を定義
            .schemaVersion(1L) // 新しいスキーマのバージョンを設定
            .migration(MyMigration()) // マイグレーション用のコードを設定
            .build()
        Realm.setDefaultConfiguration(realmConfig) // 上記の設定をRealmにセット


        val memo: Memo? = read()



        day1Content.setOnClickListener {
            val toTodayTaskIntent = Intent(this, MainActivity2::class.java)
            startActivity(toTodayTaskIntent)
        }

        if (memo !=null) {
            day1TIme.setText(memo.task)
        }

        //受け取った変数を入れる
        val str = intent.getStringExtra("EXTRA_MESSAGE")
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun read(): Memo? {
        return realm.where(Memo::class.java).findFirst()
    }
}