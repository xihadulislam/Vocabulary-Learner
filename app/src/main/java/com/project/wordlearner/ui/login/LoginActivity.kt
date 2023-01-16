package com.project.wordlearner.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.wordlearner.R
import com.project.wordlearner.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        googleLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}