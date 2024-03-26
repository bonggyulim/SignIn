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
    // ActivityResultLauncher 자료형을 전역 변수로 선언, Intent는 받아올 자료형

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
                // 입력값이 0 이거나 userMap의 id와 pw이 다를때 Toast를 띄움

            } else {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intentSignIn = Intent(this, HomeActivity::class.java)
                intentSignIn.putExtra("userID", idEditText)
                startActivity(intentSignIn)
                // 제대로된 입력이 들어왔다면, 로그인 성공 Toast 띄우고, userData를 갖고 HomeActivity를 실행
            }
        }


        btnSignUp.setOnClickListener {
            val intentSignUp = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intentSignUp)
            // resultLauncher로 intentSignUp을 실행
            // finish()가 되면 result값으로 (resultCode, intent)쌍값을 받아옴
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                // ActivityResultContracts와 ActivityResultCallback 라는 파라미터를 사용
                if (it.resultCode == RESULT_OK) {
                    // resultCode가 일치하면 intent를 받아옴
                    val id = it.data?.getStringExtra("id") ?: ""
                    val pw = it.data?.getStringExtra("pw") ?: ""
                    // getStringExtra에 key를 입력하여 값을 받아옴
                    signin_idEt.setText(id).toString()
                    signin_pwEt.setText(pw).toString()
                }
            }
    }
}

