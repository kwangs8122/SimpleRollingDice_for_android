package com.nkw.simplerollingdice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Thread.sleep

class SplshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splsh)

        sleep(3000)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}