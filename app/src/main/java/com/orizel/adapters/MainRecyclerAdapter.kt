package com.orizel.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.R
import com.orizel.models.CartProduct
import com.orizel.models.FoodProduct
import com.orizel.utils.Constants.CART_COLLECTION
import com.orizel.utils.Constants.USERS_COLLECTION
import com.squareup.picasso.Picasso


class MainRecyclerAdapter(
    val context: Context,
    private val foodProducts: MutableList<FoodProduct>
) :
    RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {

    private var quantities: Int = 1

    private var firestore = FirebaseFirestore.getInstance()
    private var firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodProducts.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemName.text = foodProducts[position].name
        holder.itemPrice.text = "₹ " + foodProducts[position].price.toString()
        //Inserting image
        Picasso.get()
            .load(foodProducts[position].imageUri)
            .into(holder.image)

        holder.addItem.setOnClickListener {
            if (quantities < 60) {
                quantities++
                holder.quantity.text = quantities.toString()
            } else {
                Toast.makeText(
                    context, "Maximum quantity Reached",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        holder.subtractItem.setOnClickListener {
            if (quantities > 1) {
                quantities--
                holder.quantity.text = quantities.toString()
            } else {
                return@setOnClickListener
            }
        }

        holder.addToCart.setOnClickListener {

            val foodProduct = CartProduct(foodProducts[position], quantities)
            addToCart(foodProduct)

            Toast.makeText(
                context, "Proceed To cart",
                Toast.LENGTH_SHORT
            ).show()
            quantities = 1
            holder.quantity.text = quantities.toString()
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.food_Product_Name)
        val itemPrice: TextView = itemView.findViewById(R.id.food_Product_price)
        val image: ImageView = itemView.findViewById(R.id.food_product_image)
        val addItem: Button = itemView.findViewById(R.id.add)
        val subtractItem: Button = itemView.findViewById(R.id.subtract)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
        val addToCart: Button = itemView.findViewById(R.id.addToCart)
    }

    //This is cart logic works with the saveUserInfo function in signUp activity
    private fun addToCart(cartProduct: CartProduct) {

        firestore.collection(USERS_COLLECTION)
            .document(firebaseAuth.uid!!)
            .collection(CART_COLLECTION)
            .document(cartProduct.foodProduct.name)
            .set(cartProduct)

    }


}