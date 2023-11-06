package com.orizel.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.orizel.R
import com.orizel.activities.CartActivity
import com.orizel.models.FoodProduct
import com.squareup.picasso.Picasso


class MainRecyclerAdapter(
    val context: Context,
    private val foodProducts: MutableList<FoodProduct>
) :
    RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {

    private var quantities: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
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

        holder.addItem.setOnClickListener {
            quantities++
            holder.quantity.text = quantities.toString()
        }

        holder.subtractItem.setOnClickListener {
            if (quantities > 0) {
                quantities--
                holder.quantity.text = quantities.toString()
            } else {
                return@setOnClickListener
            }
        }

        holder.addToCart.setOnClickListener {
            quantities = 0
            holder.quantity.text = quantities.toString()
            Toast.makeText(context, "Proceed To cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(context,CartActivity::class.java)
            intent.putExtra("QUANTITY",quantities)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.food_Product_Name)
        val itemPrice: TextView = itemView.findViewById(R.id.food_Product_price)
        val image: ImageView = itemView.findViewById(R.id.food_product_image)
        val addItem: Button = itemView.findViewById(R.id.add)
        val subtractItem: Button = itemView.findViewById(R.id.subtract)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
        val addToCart : Button = itemView.findViewById(R.id.addToCart)
    }

}