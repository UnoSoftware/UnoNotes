package com.example.myapplication

class MainList {
    private var listOfList: MutableList<ProductList> = mutableListOf<ProductList>()

    fun addList(name:String){
        val newList = ProductList(name)
        listOfList.add(newList)
    }

    fun deleteList(position: Int){
        listOfList.removeAt(position)
    }

    fun modifyName(position: Int, name: String){
        listOfList[position].modifyNameList(name)
    }
}