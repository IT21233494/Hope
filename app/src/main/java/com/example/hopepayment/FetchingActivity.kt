package com.example.hopepayment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Madjobs.PaymentAdapter
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var companyRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var comList: ArrayList<PaymentModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        companyRecyclerView = findViewById(R.id.rvCom)
        companyRecyclerView.layoutManager = LinearLayoutManager(this)
        companyRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        comList = arrayListOf<PaymentModel>()

        getEmployeesData()

    }

    private fun getEmployeesData() {

        companyRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("payments")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                comList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(PaymentModel::class.java)
                        comList.add(empData!!)
                    }
                    val mAdapter = PaymentAdapter(comList)
                    companyRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : PaymentAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivity, PaymentDetailsActivity::class.java)
                            intent.putExtra("id", comList[position].payId)
                            intent.putExtra("name", comList[position].name)
                            intent.putExtra("address", comList[position].address)
                            intent.putExtra("mobile", comList[position].mobile)
                            intent.putExtra("price", comList[position].price)
                            intent.putExtra("type", comList[position].type)
                            intent.putExtra("cardNum", comList[position].cardNum)
                            intent.putExtra("cardName", comList[position].cardName)
                            intent.putExtra("cvc", comList[position].cvc)
                            intent.putExtra("expireDate", comList[position].expireDate)
                            startActivity(intent)
                        }

                    })

                    companyRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}