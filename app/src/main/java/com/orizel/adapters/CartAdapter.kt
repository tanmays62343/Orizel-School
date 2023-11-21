package com.orizel.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.R
import com.orizel.activities.MainActivity
import com.orizel.models.CartProduct
import com.orizel.utils.Constants.CART_COLLECTION
import com.orizel.utils.Constants.USERS_COLLECTION
import com.squareup.picasso.Picasso

class CartAdapter(
    val context: Context,
    private val cartProducts: MutableList<CartProduct>
) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var numberOfItems: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_recycler_view_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.foodName.text = cartProducts[position].foodProduct.name
        holder.foodPrice.text = "₹ " + cartProducts[position].foodProduct.price.toString()
        holder.foodQuantity.text = "X " + cartProducts[position].quantity.toString()

        Picasso.get()
            .load(cartProducts[position].foodProduct.imageUri)
            .into(holder.foodImage)

        holder.foodDelete.setOnClickListener {
            deleteCartItem(position)
        }

    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    //Function to delete an item
    private fun deleteCartItem(position: Int) {

        val deletedItem = cartProducts[position]
        val firestore = FirebaseFirestore.getInstance()
        val firebaseAuth = FirebaseAuth.getInstance()

        val documentReference = firestore.collection(USERS_COLLECTION)
            .document(firebaseAuth.uid!!)
            .collection(CART_COLLECTION)
            .document(deletedItem.foodProduct.name)

        // Delete the document from Firestore it will automatically update the list
        //we can add on success listener with it
        documentReference.delete()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.food_name)
        val foodPrice: TextView = itemView.findViewById(R.id.food_price)
        val foodQuantity: TextView = itemView.findViewById(R.id.food_quantity)
        val foodImage: ImageView = itemView.findViewById(R.id.food_image)
        val foodDelete: ImageView = itemView.findViewById(R.id.food_delete)
    }

}