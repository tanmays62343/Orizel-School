package com.orizel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.orizel.R
import com.orizel.RvAdapter
import com.orizel.databinding.ActivityRvBinding

class RvActivity : AppCompatActivity() {
    lateinit var binding : ActivityRvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv1.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        binding.rv1.adapter = RvAdapter(this,)
    }
}