package com.eloam.mvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.eloam.mvvm.MainActivity
import com.eloam.mvvm.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    fun test(view: View) {

        startActivity(Intent(this, MainActivity::class.java))
    }
}