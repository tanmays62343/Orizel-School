package com.orizel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orizel.R
import com.orizel.models.FoodProduct
import com.squareup.picasso.Picasso


class MainRecyclerAdapter (val requiredContext : Context, private val foodProducts: MutableList<FoodProduct>) :
    RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rcv_items,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodProducts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemName.text = foodProducts[position].name
        holder.itemPrice.text = foodProducts[position].price.toString()
        //Inserting image
        Picasso.get().load(foodProducts[position].imageUri).into(holder.image)
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemName : TextView = itemView.findViewById(R.id.topTextView)
        val itemPrice : TextView = itemView.findViewById(R.id.rightTextView)
        val image :ImageView = itemView.findViewById(R.id.food_product_img)

    }

}