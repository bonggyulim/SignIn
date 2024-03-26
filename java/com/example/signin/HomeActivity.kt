package com.example.signin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userData = intent.getStringExtra("userID")
        // 입력받은 userID를 받아옴

        val id = userMap.get(userData)!!.id
        val homeIdTv = findViewById<TextView>(R.id.home_id_tv)
        homeIdTv.setText("ID : " + id)
        // TextView에 "ID : userID"를 저장

        val name = userMap.get(userData)!!.name
        val homeNameTv = findViewById<TextView>(R.id.home_name_tv)
        homeNameTv.setText("이름 : " + name)

        val btnFinishHome = findViewById<Button>(R.id.btn_finish_home)
        btnFinishHome.setOnClickListener {
            finish()
        }

        val imageView = findViewById<ImageView>(R.id.imageView3)
        when ((1..3).random()) {
            // 랜덤으로 imageView3의 리소스값이 정해짐
            1 -> imageView.setImageResource(R.drawable.dog)
            2 -> imageView.setImageResource(R.drawable.cat)
            3 -> imageView.setImageResource(R.drawable.bird)
        }
    }
}