package com.orizel.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.orizel.activities.MainActivity
import com.orizel.adapters.CartAdapter
import com.orizel.databinding.FragmentCartBinding
import com.orizel.models.CartProduct
import com.orizel.utils.Constants.CART_COLLECTION
import com.orizel.utils.Constants.USERS_COLLECTION

class CartFragment : Fragment() {

    private var binding : FragmentCartBinding? = null

    private lateinit var cartAdapter: CartAdapter

    private var cartProductList = mutableListOf<CartProduct>()

    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractCartList()
    }

    private fun extractCartList() {
        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        val collectionReference = firestore.collection(USERS_COLLECTION)
            .document(firebaseAuth.uid!!).collection(CART_COLLECTION)

        collectionReference.addSnapshotListener { value, error ->
            if (!isAdded) {
                // Fragment is not attached to a context, do not proceed with UI operations
                return@addSnapshotListener
            }

            if (value == null || error != null) {
                Toast.makeText(requireContext(), "Cannot fetch data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            cartProductList.clear()
            cartProductList.addAll(value.toObjects(CartProduct::class.java))
            var sum = 0
            for (i in cartProductList) {
                sum += i.foodProduct.price * i.quantity
            }
            binding?.cartTotal?.text = sum.toString()

            val cartSize = cartProductList.size
            Intent(requireContext(),MainActivity::class.java).also {
                it.putExtra("CART_SIZE",cartSize)
            }

            cartAdapter = CartAdapter(requireContext(), cartProductList)
            binding?.cartRecyclerView?.adapter = cartAdapter
        }
    }


}