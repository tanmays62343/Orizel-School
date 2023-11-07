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
    RecyclerView.Adapter<OrdersAdapter.orderViewAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): orderViewAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_items, parent, false)
        return orderViewAdapter(view)
    }

    override fun getItemCount(): Int {
        return orderItems.size
    }

    override fun onBindViewHolder(holder: orderViewAdapter, position: Int) {
        holder.date_time.text = orderItems[position].dateAndTime
        holder.food_name.text = orderItems[position].nameOfFood
        holder.location.text = orderItems[position].location
        holder.status.text = orderItems[position].status
        holder.price.text = orderItems[position].price.toString()
        holder.restaurant_name.text = orderItems[position].nameOfRestaurant
        holder.food_image.setImageResource(orderItems[position].image)
    }

    class orderViewAdapter(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val food_image : ImageView = itemView.findViewById(R.id.food_image)
        val food_name : TextView = itemView.findViewById(R.id.food_name)
        val status : TextView = itemView.findViewById(R.id.status)
        val date_time : TextView = itemView.findViewById(R.id.date_time)
        val price : TextView = itemView.findViewById(R.id.price)
        val restaurant_name : TextView = itemView.findViewById(R.id.restaurant_name)
        val location : TextView = itemView.findViewById(R.id.location)
    }

}