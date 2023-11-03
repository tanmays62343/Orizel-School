package com.orizel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.R
import com.orizel.adapters.MainRecyclerAdapter
import com.orizel.databinding.ActivityMainBinding
import com.orizel.models.FoodProduct

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    //for top action bar setup
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    //for firestore initialization
    private lateinit var firestore: FirebaseFirestore

    //for firebase authentication
    private lateinit var firebaseAuth: FirebaseAuth

    private var foodProductsList = mutableListOf<FoodProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        firebaseAuth = FirebaseAuth.getInstance()

        setupViews()   //Setting up all views Here

        //TODO : Shift the logout buttons place
        binding?.logout?.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(
                this, "Logged Out",
                Toast.LENGTH_LONG
            ).show()
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



        //Handling the bottom Navigation Bar listener
        binding?.btNavigationBar?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btNavigation_home -> {
                    Toast.makeText(this, "Hello",
                        Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.btNavigation_Cart -> {
                    Toast.makeText(this, "Cart",
                        Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.btNavigation_orders -> {
                    Toast.makeText(this, "Orders",
                        Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    Toast.makeText(this, "Error",
                        Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }


    }

    //Aggregating all the views here
    private fun setupViews() {
        setupDrawerLayout()
        setupRecyclerView()
        setupFireStore()
    }

    //Setting up the firebase Database
    private fun setupFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("foodProduct")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Cannot fetch data",
                    Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            foodProductsList.clear()
            foodProductsList.addAll(value.toObjects(FoodProduct::class.java))
            binding?.mainRecyclerView?.adapter?.notifyDataSetChanged()
        }
    }

    //Recycler view setup
    private fun setupRecyclerView() {
        // data items
        binding!!.mainRecyclerView.adapter = MainRecyclerAdapter(this,foodProductsList)
        binding?.mainRecyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
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