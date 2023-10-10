package com.orizel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.orizel.R
import com.orizel.databinding.ActivityIntroBinding
import java.lang.Exception

class IntroActivity : AppCompatActivity() {

    private var binding : ActivityIntroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser != null){
            redirect("MAIN")
        }

        binding?.getStarted?.setOnClickListener {
            redirect("LOGIN")
        }

    }

    private fun redirect(name:String){
        val intent = when(name){
            "LOGIN" -> Intent(this,LoginActivity::class.java)
            "MAIN" -> Intent(this,MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }

}