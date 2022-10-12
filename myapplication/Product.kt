package com.example.myapplication

import com.example.myapplication.Product

class Product(var name: String, var price: Float, var quantity: Int) {
    private var isBought: Boolean

    init {
        isBought = false
    }

    fun modifyProduct(name: String, price: Float, quantity: Int){
        this.name = name
        this.price = price
        this.quantity = quantity
    }

    fun checkProduct(){
        isBought = !isBought
    }
}