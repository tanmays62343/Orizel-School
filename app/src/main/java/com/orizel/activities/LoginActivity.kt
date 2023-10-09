package com.orizel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orizel.R
import com.orizel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var binding : ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)


    }
}