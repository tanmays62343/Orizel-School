package com.orizel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.orizel.R
import com.orizel.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private var binding: ActivitySignupBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //initializing firebase
        firebaseAuth = FirebaseAuth.getInstance()

        binding?.SignupBtn?.setOnClickListener {
            signUpUser()
        }
        binding?.tvLogin?.setOnClickListener {
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //creating a user using firebase
    private fun signUpUser() {
        val email = binding?.etSemail?.text.toString()
        val password = binding?.etSpassword?.text.toString()
        val confirmPassword = binding?.etCnfrmPass?.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(
                this, "Fields cannot be Blank",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(
                this, "Passwords do not Match",
                Toast.LENGTH_SHORT
            ).show()
        }
        //Here user is created
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Signup Successful",
                        Toast.LENGTH_SHORT).show()
                    intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this, "Error Creating User",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}