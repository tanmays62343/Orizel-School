package com.orizel.models

data class CartProduct(
    var foodProduct: FoodProduct,
    var quantity : Int
){
    constructor() : this(FoodProduct(),1)
}
