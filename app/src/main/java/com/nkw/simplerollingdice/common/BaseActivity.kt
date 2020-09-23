package com.nkw.simplerollingdice.common

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    var vibe: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vibe = getSystemService(VIBRATOR_SERVICE) as Vibrator
    }

    protected fun toast(mContext: Context, message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }
}