package com.astra.notes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.product_layout.view.*

class ProductAdapter(private val activity: Activity, private val products: ArrayList<String>, private val amounts: ArrayList<Int>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount() = products.size

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        val amount = amounts[position]
        holder.layout.product_name.setText(product)
        holder.layout.amount_num.setText(amount.toString())
        holder.layout.delete_prod.setOnClickListener {
            products.removeAt(position)
            amounts.removeAt(position)
            notifyDataSetChanged()
        }
    }
}