package com.tubianto.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
    }

    fun kembali(view: View) {
        val intent = Intent(this@InfoActivity, MainActivity::class.java)
        startActivity(intent)
    }
}