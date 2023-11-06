package com.orizel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orizel.R
import com.orizel.databinding.ActivityOrdersBinding

class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}