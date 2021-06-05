package app.kato.nim.tasks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        day1Content.setOnClickListener {
            val toTodayTaskIntent = Intent(this, MainActivity2::class.java)
            startActivity(toTodayTaskIntent)
        }

        //受け取った変数を入れる
        val str = intent.getStringExtra("EXTRA_MESSAGE")
    }
}