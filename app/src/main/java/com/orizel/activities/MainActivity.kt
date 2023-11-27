package com.orizel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.orizel.R
import com.orizel.databinding.ActivityMainBinding
import com.orizel.fragments.CartFragment
import com.orizel.fragments.FoodProductsFragment
import com.orizel.fragments.OrdersFragment

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

    private var cartSize : Int = 0

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
            finish()
        }



        //Handling the bottom Navigation Bar listener
        binding.btNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btNavigation_home -> {
                    setupFragment(foodProductsFragment)
                   true
                }
                R.id.btNavigation_Cart -> {
                    setupFragment(cartFragment)
                    true
                }
                R.id.btNavigation_orders -> {
                    setupFragment(ordersFragment)
                    true
                }
                else -> {
                    Toast.makeText(this, "Error",
                        Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }

        //Handling the drawer menu items
        binding.drawerNavigation.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.profile -> {
                    Intent(this,ProfileActivity::class.java).also {
                        startActivity(it)
                    }
                    true
                }
                R.id.aboutUs -> {
                    Intent(this,AboutUsActivity::class.java).also {
                        startActivity(it)
                    }
                    true
                }
                R.id.rateUs ->{
                    //TODO (When uploaded to playStore)
                    true
                }
                else -> {
                    Toast.makeText(this,
                        "Error", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }

        cartSize = intent.getIntExtra("CART_SIZE",0)

        binding.btNavigationBar.getOrCreateBadge(R.id.btNavigation_Cart).apply{
            if(cartSize > 0) {
                number = cartSize
            }
        }

    }

    //Aggregating all the views here
    private fun setupViews() {
        setupDrawerLayout()
        setupFragment(foodProductsFragment)
    }

    //for setting up the fragments
    private fun setupFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id,fragment)
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