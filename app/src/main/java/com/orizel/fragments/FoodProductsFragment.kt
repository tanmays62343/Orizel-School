package com.orizel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.R
import com.orizel.utils.Constants.FOOD_PRODUCTS
import com.orizel.adapters.MainRecyclerAdapter
import com.orizel.databinding.FragmentFoodProductsBinding
import com.orizel.models.FoodProduct

//refer philip lackner for the fragment constructor reference
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
        extractProductList()
    }

    //Setting up the firebase Database
    private fun extractProductList() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection(FOOD_PRODUCTS)
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