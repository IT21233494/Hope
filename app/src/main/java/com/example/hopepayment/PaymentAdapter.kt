package com.example.Madjobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hopepayment.PaymentModel
import com.example.hopepayment.R

class PaymentAdapter(private val payments: ArrayList<PaymentModel>) :
    RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pay_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCom = payments[position]
        holder.customerName.text = currentCom.name
        holder.cardType.text = currentCom.type
        holder.cardNumber.text = currentCom.cardNum
        holder.price.text = currentCom.price
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val customerName : TextView = itemView.findViewById(R.id.showName)
        val cardType : TextView = itemView.findViewById(R.id.showType)
        val cardNumber : TextView = itemView.findViewById(R.id.showNumber)
        val price : TextView = itemView.findViewById(R.id.showPrice)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}