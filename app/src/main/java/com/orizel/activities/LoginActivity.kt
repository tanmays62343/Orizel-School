package com.orizel.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.orizel.R
import com.orizel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var binding : ActivityLoginBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private var user : FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding?.loginButton?.setOnClickListener {
            login()
        }

        binding?.tvSignup?.setOnClickListener {
            binding?.tvSignup?.setTextColor(Color.parseColor("#551A8B"))
            intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    //Here we are authenticating the user
    private fun login(){
        val email = binding?.etEmail?.text.toString()
        val password = binding?.etPassword?.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Fields cannot be Blank",
                Toast.LENGTH_SHORT).show()
            return
        }

        //Login function of firebase
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    user = firebaseAuth.currentUser
                    if(user!!.isEmailVerified) {
                        Toast.makeText(this, "Login Successful",
                            Toast.LENGTH_SHORT).show()
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Please Verify your email",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Authentication Failed",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

}