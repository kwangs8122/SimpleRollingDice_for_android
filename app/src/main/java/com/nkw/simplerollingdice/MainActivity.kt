package com.nkw.simplerollingdice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nkw.simplerollingdice.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        btnOne.setOnClickListener(this)
        btnTwo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
//        TODO("Not yet implemented")

        when (v!!.id) {
            R.id.btnOne -> {
                toast(this, "주사위 1개 버튼 클릭!!")

                val intent: Intent = Intent(this, OneDiceActivity::class.java)
                startActivity(intent)
            }
            R.id.btnTwo -> {
                toast(this, "주사위 2개 버튼 클릭!!")

                val intent: Intent = Intent(this, TwoDicesActivity::class.java)
                startActivity(intent)
            }
            else -> {

            }
        }
    }
}