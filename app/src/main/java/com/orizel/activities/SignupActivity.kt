package com.orizel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.orizel.R
import com.orizel.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private var binding : ActivitySignupBinding? = null
    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //initializing firebase
        firebaseAuth = FirebaseAuth.getInstance()

    }

    //creating a user using firebase
    private fun signUpUser(){
        var email = binding?.etSemail?.text.toString()
        var password = binding?.etSpassword?.text.toString()
        var confirmPassword = binding?.etCnfrmPass?.text.toString()

        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Fields cannot be Blank", Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmPassword){
            Toast.makeText(this, "Passwords do not Match", Toast.LENGTH_SHORT).show()
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    //TODO Redirection code
                }
                else{
                    Toast.makeText(this, "Error Creating User",
                        Toast.LENGTH_SHORT).show()
                }
            }


    }

}