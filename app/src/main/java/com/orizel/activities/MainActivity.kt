package com.orizel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.R
import com.orizel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    //for top action bar setup
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    //for firestore initialization
    private lateinit var firestore: FirebaseFirestore

    //for firebase authentication
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        firebaseAuth = FirebaseAuth.getInstance()

        setupViews()

        binding?.logout?.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(
                this, "Logged Out",
                Toast.LENGTH_LONG
            ).show()
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding?.btNavigationBar?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btNavigation_home -> {
                    Toast.makeText(
                        this, "Hello",
                        Toast.LENGTH_SHORT
                    ).show()
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
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }


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
    }

    //we are telling here that we have our own action bar
    private fun setupDrawerLayout() {
        setSupportActionBar(binding?.topAppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding?.drawerLayout,
            R.string.app_name, R.string.app_name
        )
        actionBarDrawerToggle.syncState()
    }

    //Here we are telling that we have handled the top bar actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}