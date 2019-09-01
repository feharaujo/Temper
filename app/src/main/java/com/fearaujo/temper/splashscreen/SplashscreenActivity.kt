package com.fearaujo.temper.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fearaujo.temper.MainActivity
import android.content.Intent

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

}