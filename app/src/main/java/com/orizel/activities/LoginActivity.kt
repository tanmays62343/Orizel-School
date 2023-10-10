package com.orizel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.orizel.R
import com.orizel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var binding : ActivityLoginBinding? = null
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding?.loginButton?.setOnClickListener {
            login()
        }

        binding?.tvSignup?.setOnClickListener {
            intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun login(){
        val email = binding?.etEmail?.text.toString()
        val password = binding?.etPassword?.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Fields cannot be Blank",
                Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Login Successful",
                        Toast.LENGTH_SHORT).show()
                    intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Authentication Failed",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

}