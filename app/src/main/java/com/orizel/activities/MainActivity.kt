package com.orizel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import com.orizel.R
import com.orizel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //for top action bar setup
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    //for firebase authentication
    private lateinit var firebaseAuth: FirebaseAuth

    //initializing the fragments
    private val foodProductsFragment = FoodProductsFragment()
    private val cartFragment = CartFragment()
    private val ordersFragment = OrdersFragment()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        setupViews()   //Setting up all views Here

        //TODO : Shift the logout buttons place
        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(
                this, "Logged Out",
                Toast.LENGTH_LONG
            ).show()
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



        //Handling the bottom Navigation Bar listener
        binding.btNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btNavigation_home -> {
                    setupFragment()
                   true
                }
                R.id.btNavigation_Cart -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.fragmentContainer.id,cartFragment)
                        commit()
                    }
                    true
                }
                R.id.btNavigation_orders -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.fragmentContainer.id,ordersFragment)
                        commit()
                    }
                    true
                }
                else -> {
                    Toast.makeText(this, "Error",
                        Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }

        binding.drawerNavigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> {
                    Intent(this,ProfileActivity::class.java)
                    true
                }

                else -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }

        binding.btNavigationBar.getOrCreateBadge(R.id.btNavigation_Cart).apply{
            number = 10
        }

    }

    //Aggregating all the views here
    private fun setupViews() {
        setupDrawerLayout()
        setupFragment()
    }

    //setting up the initial fragment
    private fun setupFragment(){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id,foodProductsFragment)
            commit()
        }
    }

    //we are telling here that we have our own action bar
    private fun setupDrawerLayout() {
        setSupportActionBar(binding.topAppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout,
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