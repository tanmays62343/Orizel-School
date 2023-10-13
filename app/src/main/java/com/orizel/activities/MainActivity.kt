package com.orizel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.orizel.R
import com.orizel.databinding.ActivityMainBinding

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
        //TODO : Aman will take care of it
        binding?.rcvItem?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
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