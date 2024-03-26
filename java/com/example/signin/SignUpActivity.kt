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

            } else if(userMap.containsKey(idEditText)) {
                Toast.makeText(this, "사용중인 id 입니다.", Toast.LENGTH_SHORT).show()

            } else {
                var userData = User(nameEditText, idEditText, pwEditText)
                userMap.put(idEditText, userData)

                intent.putExtra("id", idEditText)
                intent.putExtra("pw", pwEditText)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}

var userMap = mutableMapOf<String, User>()

data class User (
    val name: String,
    val id: String,
    val pw: String
)
