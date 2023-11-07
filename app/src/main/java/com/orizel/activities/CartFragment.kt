package com.orizel.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orizel.R
import com.orizel.databinding.FragmentCartBinding
import com.orizel.models.FoodProduct

class CartFragment : Fragment(R.layout.fragment_cart) {

    private var binding : FragmentCartBinding? = null

    private var cartItems : ArrayList<FoodProduct>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}