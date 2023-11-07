package com.orizel.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.R
import com.orizel.adapters.MainRecyclerAdapter
import com.orizel.databinding.FragmentFoodProductsBinding
import com.orizel.models.FoodProduct


class FoodProductsFragment : Fragment(R.layout.fragment_food_products) {

    private var binding: FragmentFoodProductsBinding? = null

    //for firestore initialization
    private lateinit var firestore: FirebaseFirestore

    //for holding list coming from firebase
    private var foodProductsList = mutableListOf<FoodProduct>()

    /*//This is how we create view of fragment (Sir's method)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.fragment_food_products,
            container,
            false
        )

        return binding!!.root
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodProductsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFireStore()
    }

    //Setting up the firebase Database
    private fun setupFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("foodProduct")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(requireContext(), "Cannot fetch data",
                    Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            foodProductsList.clear()
            foodProductsList.addAll(value.toObjects(FoodProduct::class.java))
            binding?.mainRecyclerView?.adapter = MainRecyclerAdapter(
                requireContext(),
                foodProductsList)

            /*binding?.mainRecyclerView?.adapter?.notifyDataSetChanged()
            we do not require this here*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // Avoid memory leaks
    }
}