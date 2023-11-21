package com.orizel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orizel.R
import com.orizel.models.Orders

class OrdersAdapter(val requiredContext: Context, private val orderItems : ArrayList<Orders>) :
    RecyclerView.Adapter<OrdersAdapter.OrderViewAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_items, parent, false)
        return OrderViewAdapter(view)
    }

    override fun getItemCount(): Int {
        return orderItems.size
    }

    override fun onBindViewHolder(holder: OrderViewAdapter, position: Int) {
        holder.datetime.text = orderItems[position].dateAndTime
        holder.foodName.text = orderItems[position].nameOfFood

        holder.status.text = orderItems[position].status
        holder.price.text = orderItems[position].price.toString()

        holder.foodImage.setImageResource(orderItems[position].image)
    }

    class OrderViewAdapter(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val foodImage : ImageView = itemView.findViewById(R.id.foood_image)
        val foodName : TextView = itemView.findViewById(R.id.foood_name)
        val status : TextView = itemView.findViewById(R.id.status)
        val datetime : TextView = itemView.findViewById(R.id.date_time)
        val price : TextView = itemView.findViewById(R.id.price)

    }

}