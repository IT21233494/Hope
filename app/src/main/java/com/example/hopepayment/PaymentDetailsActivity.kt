package com.example.hopepayment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class PaymentDetailsActivity : AppCompatActivity(){
    private lateinit var uName: EditText
    private lateinit var uAddress: EditText
    private lateinit var uMobile: EditText
    private lateinit var price: EditText
    private lateinit var cType: EditText
    private lateinit var cNumber: EditText
    private lateinit var cName: EditText
    private lateinit var cCvc: EditText
    private lateinit var cExpireDate: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)
        dbRef = FirebaseDatabase.getInstance().getReference("payments")
        initView()
        setValuesToViews()

    }

    private fun initView() {
        uName = findViewById(R.id.editName)
        uAddress = findViewById(R.id.editAddress)
        uMobile = findViewById(R.id.editMobile)
        price = findViewById(R.id.editPrice)
        cType = findViewById(R.id.editCType)
        cNumber = findViewById(R.id.editCNuber)
        cName = findViewById(R.id.editCName)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        uName.setText(intent.getStringExtra("name"))
        uAddress.setText(intent.getStringExtra("address"))
        uMobile.setText(intent.getStringExtra("mobile"))
        price.setText(intent.getStringExtra("price"))
        cType.setText(intent.getStringExtra("type"))
        cNumber.setText(intent.getStringExtra("cardNum"))
        cName.setText(intent.getStringExtra("cardName"))

    }

    fun updatePayment(view: View) {
        val name = uName.text.toString()
        val address = uAddress.text.toString()
        val mobile = uMobile.text.toString()
        val price = price.text.toString()
        val type = cType.text.toString()
        val number = cNumber.text.toString()
        val namecard = cName.text.toString()
        val cvc = intent.getStringExtra("cvc").toString()
        val exDate = intent.getStringExtra("expireDate").toString()
        val payId = intent.getStringExtra("id").toString()
        if (name.isNotEmpty() && type.isNotEmpty() && namecard.isNotEmpty() && price.isNotEmpty() ){

            val pay = PaymentModel(
                payId = payId,
                name = name,
                address = address,
                mobile = mobile ,
                price = price,
                type = type,
                cardNum = number,
                cardName = namecard,
                cvc = cvc,
                expireDate = exDate

            )

            dbRef.child(payId).setValue(pay)
                .addOnCompleteListener {
                    Toast.makeText(this, "Payment Details Updated successfully", Toast.LENGTH_LONG).show()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
    fun deletePayment(view: View) {
        val paymentId = intent.getStringExtra("id").toString()
        if (paymentId.isNotEmpty()){
            val intent = Intent(this, FetchingActivity::class.java)
            dbRef.child(paymentId).setValue(null)
                .addOnCompleteListener {
                    Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }

}