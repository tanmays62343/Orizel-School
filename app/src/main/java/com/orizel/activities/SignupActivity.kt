package com.orizel.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.utils.Constants.USERS_COLLECTION
import com.orizel.databinding.ActivitySignupBinding
import com.orizel.models.User

class SignupActivity : AppCompatActivity() {

    private var binding: ActivitySignupBinding? = null

    //for firebase authentication
    private lateinit var firebaseAuth: FirebaseAuth

    //for firebase firestore database
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //initializing firebase
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding?.SignupBtn?.setOnClickListener {
            signUpUser()
        }
        binding?.tvLogin?.setOnClickListener {
            binding?.tvLogin?.setTextColor(Color.parseColor("#551A8B"))
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //creating a user using firebase
    private fun signUpUser() {
        val name = binding?.etSname?.text.toString()
        val email = binding?.etSemail?.text.toString()
        val password = binding?.etSpassword?.text.toString()
        val confirmPassword = binding?.etCnfrmPass?.text.toString()

        val mUser = User(name, email)

        //General ID and Password Authentication
        if (email.isBlank() || password.isBlank()
            || confirmPassword.isBlank() || name.isBlank()
        ) {
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

        //firebase function for creating a user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { emailTask ->
                            if (emailTask.isSuccessful) {
                                Toast.makeText(
                                    this, "Verification email sent",
                                    Toast.LENGTH_LONG
                                ).show()
                                saveUserInfo(user.uid,mUser)
                                binding?.etSemail?.text?.clear()
                                binding?.etSpassword?.text?.clear()
                                binding?.etCnfrmPass?.text?.clear()
                                firebaseAuth.signOut()
                                startActivity(Intent(this, LoginActivity::class.java))
                            } else {
                                Toast.makeText(
                                    this, "Failed to sent Verification email",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        this, "Error Creating User",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun saveUserInfo(userUid: String, user: User) {
        firestore.collection(USERS_COLLECTION)
            .document(userUid)
            .set(user)
    }


}