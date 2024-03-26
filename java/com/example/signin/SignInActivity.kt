package com.example.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class SignInActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnSignIn = findViewById<Button>(R.id.btn_signin)
        val btnSignUp = findViewById<Button>(R.id.btn_signup)

        val signin_idEt = findViewById<EditText>(R.id.signin_id_et)
        val signin_pwEt = findViewById<EditText>(R.id.signin_pw_et)

        btnSignIn.setOnClickListener {
            val idEditText = signin_idEt.text.toString()
            val pwEditText = signin_pwEt.text.toString()

            if (idEditText.length == 0 || pwEditText.length == 0 || userMap.get(idEditText)?.pw != pwEditText) {
                Toast.makeText(applicationContext, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intentSignIn = Intent(this, HomeActivity::class.java)
                intentSignIn.putExtra("userData", idEditText)
                startActivity(intentSignIn)
            }
        }


        btnSignUp.setOnClickListener {
            val intentSignUp = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intentSignUp)
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val id = it.data?.getStringExtra("id") ?: ""
                    val pw = it.data?.getStringExtra("pw") ?: ""
                    signin_idEt.setText(id).toString()
                    signin_pwEt.setText(pw).toString()
                }
            }
    }
}

