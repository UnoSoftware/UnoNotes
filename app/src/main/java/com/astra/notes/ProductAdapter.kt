package com.astra.notes

import android.app.Activity
import android.graphics.Paint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.product_layout.view.*

class ProductAdapter(
    private val activity: Activity,
    private val products: ArrayList<String>,
    private val amounts: ArrayList<Int>,
    private val checks: ArrayList<Boolean>
    ) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount() = products.size

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        val amount = amounts[position]
        val checked = checks[position]
        holder.layout.product_name.setText(product)
        holder.layout.amount_num.setText(amount.toString())
        holder.layout.product_check.isChecked = checked

        if (holder.layout.product_check.isChecked) {
            holder.layout.product_name.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        holder.layout.product_check.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.layout.product_name.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
            } else {
                holder.layout.product_name.apply {
                    paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }

        holder.layout.delete_prod.setOnClickListener {
            products.removeAt(position)
            amounts.removeAt(position)
            checks.removeAt(position)
            notifyDataSetChanged()
        }
    }
}