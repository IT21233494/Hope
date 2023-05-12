package com.example.mad.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.example.mad.model.CharityModel
import com.example.mad.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@Suppress("DEPRECATION")
class RegisterCharity : AppCompatActivity() {

    private lateinit var submitBtnC: Button
    private lateinit var dbRef:DatabaseReference

    private lateinit var nameEditText: EditText
    private lateinit var nameEditFounder: EditText
    private lateinit var emailEditText : EditText
    private lateinit var nameEditPhone: EditText
    private lateinit var nameEditMotive:EditText
    private lateinit var checkBox: CheckBox

    private lateinit var selectImageButton: Button
    private lateinit var imageView: ImageView

    private val PICK_IMAGE_REQUEST = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_charity)

        nameEditText = findViewById(R.id.nameEditText)
        nameEditFounder = findViewById(R.id.nameEditFounder)
        emailEditText = findViewById(R.id.emailEditText)
        nameEditPhone = findViewById(R.id.nameEditPhone)
        nameEditMotive = findViewById(R.id.nameEditMotive)
        checkBox= findViewById(R.id.checkBox)
        selectImageButton=findViewById(R.id.selectImageButton)
        imageView=findViewById(R.id.imageView)
        submitBtnC=findViewById(R.id.submitBtnC)

        selectImageButton = findViewById(R.id.selectImageButton)
        imageView = findViewById(R.id.imageView)




        dbRef = FirebaseDatabase.getInstance().getReference("charity")


        submitBtnC.setOnClickListener {
            saveCharityData()
        }

        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }


    }

    private fun saveCharityData() {
        val charName = nameEditText.text.toString()
        val charFou = nameEditFounder.text.toString()
        val charEmail = emailEditText.text.toString()
        val charPhone = nameEditPhone.text.toString()
        val charMot = nameEditMotive.text.toString()




        if(charName.isEmpty()){
            nameEditText.error = "Please Enter Charity name"
        }

        if(charFou.isEmpty()){
            nameEditFounder.error = "Please Enter Charity Founder name"
        }

        if(charEmail.isEmpty()){
            emailEditText.error = "Please Enter Email"
        }

        if(charPhone.isEmpty()){
            nameEditPhone.error = "Please Enter Phone Number"
        }
        if(charMot.isEmpty()){
            nameEditMotive.error = "Please enter the motivation of the charity"
        }

        if(!(checkBox.isChecked)){
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show()
        }


        val charId = dbRef.push().key!!

        val charity = CharityModel(charId ,charName,charFou,charEmail,charPhone,charMot)

        dbRef.child(charId).setValue(charity)
            .addOnCompleteListener {
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                nameEditText.text.clear()
                emailEditText.text.clear()
                nameEditFounder.text.clear()
                nameEditPhone.text.clear()
                nameEditMotive.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()

            }

    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            imageView.setImageURI(selectedImageUri)
        }
    }
}