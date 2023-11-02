package com.orizel.models


data class FoodProduct(
    var name : String,
    var price : Int,
    var imageUri : String
){
    constructor() : this("",0,"")
}
