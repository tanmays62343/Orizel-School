package com.orizel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.orizel.R
import com.orizel.databinding.ActivityMainBinding
import com.orizel.models.FoodProduct

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    //for top action bar setup
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    //for firestore initialization
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViews()

        // data items
        var detailsOfItems = ArrayList<FoodProduct>()
        detailsOfItems.add(FoodProduct("Dabeli",50, R.drawable.img))
        detailsOfItems.add(FoodProduct("Noodles",120, R.drawable.img_1))
        detailsOfItems.add(FoodProduct("Pizza",350, R.drawable.img_2))
        detailsOfItems.add(FoodProduct("Burger",80, R.drawable.img_3))
        detailsOfItems.add(FoodProduct("Thali",180, R.drawable.img_4))


        binding?.rcvItem?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding!!.rcvItem.adapter = RcvAdapter(this,detailsOfItems)
    }

    private fun setupViews() {
        setupDrawerLayout()
        setupRecyclerView()
        setupFireStore()
    }

    private fun setupFireStore() {
        firestore = FirebaseFirestore.getInstance()
        // val collectionReference = firestore.collection("")
    }

    private fun setupRecyclerView() {
//        var detailsOfItems = ArrayList<FoodProduct>()
//        detailsOfItems.add(FoodProduct("Burger",150, R.drawable.img))
//        detailsOfItems.add(FoodProduct("Burger",150, R.drawable.img_1))

        //TODO : Aman will take care of it
//        binding?.rcvItem?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        binding?.rcvItem?.layoutManager = LayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    //we are telling here that we have our own action bar
    private fun setupDrawerLayout() {
        setSupportActionBar(binding?.topAppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding?.drawerLayout,
            R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    //Here we are telling that we have handled the top bar actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}