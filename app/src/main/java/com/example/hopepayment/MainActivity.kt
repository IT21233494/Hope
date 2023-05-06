package com.example.hopepayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addPayment(view: View) {
        val intent = Intent(this,AddFirstActivity::class.java)
        startActivity(intent)
    }
    fun viewPayments(view: View) {
        val intent = Intent(this,FetchingActivity::class.java)
        startActivity(intent)
    }
}