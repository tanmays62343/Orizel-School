package com.orizel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orizel.R
import com.orizel.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment(R.layout.fragment_orders) {

    private var binding : FragmentOrdersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding?.root
    }



}