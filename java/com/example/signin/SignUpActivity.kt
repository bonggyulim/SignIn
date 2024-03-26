package com.example.signin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnSignUp = findViewById<Button>(R.id.btn_signup)
        btnSignUp.setOnClickListener {
            val signup_nameEt = findViewById<EditText>(R.id.signup_name_et)
            val signup_idEt = findViewById<EditText>(R.id.signup_id_et)
            val signup_pwEt = findViewById<EditText>(R.id.signup_pw_et)
            val nameEditText = signup_nameEt.text.toString()
            val idEditText = signup_idEt.text.toString()
            val pwEditText = signup_pwEt.text.toString()

            if (nameEditText.length == 0 || idEditText.length == 0 || pwEditText.length == 0) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                // 입력값이 하나라도 0일때 Toast 메시지를 띄움

            } else if(userMap.containsKey(idEditText)) {
                Toast.makeText(this, "사용중인 id 입니다.", Toast.LENGTH_SHORT).show()
                // userMap에서 존재하는 id가 있으면, Toast 메시지를 띄움

            } else {
                var userData = User(nameEditText, idEditText, pwEditText)
                userMap.put(idEditText, userData)
                // 데이터클래스의 객체 생성 및 userMap에 아이디와 객체를 저장

                intent.putExtra("id", idEditText)
                intent.putExtra("pw", pwEditText)
                // 값을 저장할 intent 생성
                setResult(RESULT_OK, intent)
                // 결과값으로 resultCode와 intent를 반환
                finish()
            }
        }
    }
}

var userMap = mutableMapOf<String, User>()
// userID와 User인스턴스를 받는 map

data class User (
    val name: String,
    val id: String,
    val pw: String
)
