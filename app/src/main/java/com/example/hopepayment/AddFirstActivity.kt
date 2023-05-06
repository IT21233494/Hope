package com.example.hopepayment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddFirstActivity : AppCompatActivity() {

    private lateinit var cusName: EditText
    private lateinit var cusAddress: EditText
    private lateinit var cusMobile: EditText
    private lateinit var price: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_first)


        cusName = findViewById(R.id.cusName)
        cusAddress = findViewById(R.id.address)
        cusMobile = findViewById(R.id.phoneNumber)
        price = findViewById(R.id.price)

    }

    fun goSecondStep(view: View) {
        val name = cusName.text.toString()
        val address = cusAddress.text.toString()
        val mobile = cusMobile.text.toString()
        val price = price.text.toString()

        if (address.isNotEmpty() && mobile.isNotEmpty() && price.isNotEmpty() && name.isNotEmpty() ){
            val intent = Intent(this,AddSecondActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("address", address)
            intent.putExtra("mobile", mobile)
            intent.putExtra("price", price)
            startActivity(intent)
        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }

    }
}


