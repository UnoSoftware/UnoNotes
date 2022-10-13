package com.astra.notes

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.card_notes.view.*

class NoteAdapter(private val activity: Activity, private val names: MutableList<String>, private val subitles: MutableList<String>,
                  private val products: MutableList<ArrayList<String>>, private val amounts: MutableList<ArrayList<Int>>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_notes, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = names[position]
        val subtitle = subitles[position]
        val products_temp = products[position]
        val amounts_temp = amounts[position]

        holder.layout.title_tv.text = name
        holder.layout.subtitle_tv.text = subtitle

        holder.layout.note_button.setOnClickListener {
            val intent = Intent(activity, NotesViewActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("subtitle", subtitle)
            intent.putExtra("products", products_temp)
            intent.putExtra("amounts", amounts_temp)
            activity.startActivity(intent)
        }
    }
}