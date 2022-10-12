package com.example.myapplication

import com.example.myapplication.Product
import java.util.ArrayList

class ProductList(name: String) {
    private var name: String
    private var products: MutableList<Product>

    init {
        products  = mutableListOf<Product>()
        this.name = name
    }

    fun addProduct(name: String, price: Float, quantity: Int){
        val newProduct = Product(name, price, quantity)
        products.add(newProduct)
    }

    fun deleteProduct(position: Int){
        products.removeAt(position)
    }

    fun modifyListProduct(position: Int, name: String, price: Float, quantity: Int){
        products[position].modifyProduct(name, price, quantity)
    }

    fun modifyNameList(name: String){
        this.name = name
    }
}