package com.astra.notes

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.product_layout.view.*

class ProductAdapter(private val activity: Activity, private val products: ArrayList<String>, private val amounts: ArrayList<Long>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        val amount = amounts[position]
        holder.layout.product_name.text = product
        holder.layout.amount_num.text = amount.toString()


    }
}