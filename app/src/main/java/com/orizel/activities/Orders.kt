package com.orizel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.orizel.R
import com.orizel.adapters.MainRecyclerAdapter
import com.orizel.adapters.OrdersAdapter
import com.orizel.databinding.ActivityOrdersBinding
import com.orizel.models.OrderItems

class Orders : AppCompatActivity() {
    private var binding: ActivityOrdersBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }
    private fun setupRecyclerView() {
        // data items
        var detailsOfItems = ArrayList<OrderItems>()
//        detailsOfItems.add(FoodProduct("Dabeli","",50, R.drawable.img))
//        detailsOfItems.add(FoodProduct("Noodles",120, R.drawable.img_1))
//        detailsOfItems.add(FoodProduct("Pizza",350, R.drawable.img_2))
//        detailsOfItems.add(FoodProduct("Burger",80, R.drawable.img_3))
//        detailsOfItems.add(FoodProduct("Thali",180, R.drawable.img_4))

        detailsOfItems.add((OrderItems("Dabeli","Apna Khana",50,"16/10/23, 3:15PM","In progress",R.drawable.img,"K.K Nagar")))
        detailsOfItems.add((OrderItems("Noodles","China Town",120,"12/19/23, 4:18PM","Delivered",R.drawable.img_1,"Prabhat chowk")))
        detailsOfItems.add((OrderItems("Pizza","Pizza Hut",350,"1/9/23, 11:15AM","Canceled",R.drawable.img_2,"Kalasagar Mall")))
        detailsOfItems.add((OrderItems("Burger","China Town",80,"13/8/23, 2:15PM","Delivered",R.drawable.img_3,"Prabhat Chowk")))
        detailsOfItems.add((OrderItems("Thali","Mintu Restaurant",180,"11/8/23, 12:18PM","Delivered",R.drawable.img_4,"Ghatlodia")))

        binding?.rcvOrders?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding!!.rcvOrders.adapter = OrdersAdapter(this,detailsOfItems)



    }

}