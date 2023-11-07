package com.orizel.models

data class Orders(
    var nameOfFood : String,
    var nameOfRestaurant : String,
    var price : Int,
    var dateAndTime : String,
    var status : String,
    var image : Int,
    var location : String
)
