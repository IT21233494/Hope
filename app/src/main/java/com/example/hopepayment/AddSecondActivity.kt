package com.example.hopepayment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddSecondActivity : AppCompatActivity() {

    private lateinit var cType: EditText
    private lateinit var cNumber: EditText
    private lateinit var cName: EditText
    private lateinit var cCvc: EditText
    private lateinit var cExDate: EditText

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_second)


        cType = findViewById(R.id.cardType)
        cNumber = findViewById(R.id.cardNumber)
        cName = findViewById(R.id.cardUserName)
        cCvc = findViewById(R.id.cardcvc)
        cExDate = findViewById(R.id.cardExDate)
        dbRef = FirebaseDatabase.getInstance().getReference("payments")


    }
    fun addRecord(view: View) {
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val mobile = intent.getStringExtra("mobile")
        val price = intent.getStringExtra("price")
        val type = cType.text.toString()
        val number = cNumber.text.toString()
        val nameCard = cName.text.toString()
        val cvc = cCvc.text.toString()
        val date = cExDate.text.toString()

        if (type.isNotEmpty() && number.isNotEmpty() && nameCard.isNotEmpty() && cvc.isNotEmpty() && date.isNotEmpty() ){
            val intent = Intent(this,AddSecondActivity::class.java)
            val paymentId = dbRef.push().key!!

            val company = PaymentModel(
                payId = paymentId ?: "",
                name = name ?: "",
                address = address ?: "",
                mobile = mobile ,
                price = price ?: "",
                type = type,
                cardNum = number,
                cardName = nameCard,
                cvc = cvc,
                expireDate = date

            )

            dbRef.child(paymentId).setValue(company)
                .addOnCompleteListener {
                    Toast.makeText(this, "Payment successful", Toast.LENGTH_LONG).show()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            //startActivity(intent)
        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }



}