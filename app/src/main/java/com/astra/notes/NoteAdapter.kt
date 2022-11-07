package com.astra.notes

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import androidx.core.graphics.toColorInt
import kotlinx.android.synthetic.main.card_notes.view.*

class NoteAdapter(private val activity: Activity, private val names: MutableList<String>, private val subitles: MutableList<String>, private val colors: MutableList<String>,
                  private val products: MutableList<ArrayList<String>>, private val amounts: MutableList<ArrayList<Int>>, private val id: Int, private val idsnotas: MutableList<String>
)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_notes, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = names[position]
        val subtitle = subitles[position]
        val color = colors[position]
        val products_temp = products[position]
        val amounts_temp = amounts[position]
        val id2 = idsnotas[position]

        holder.layout.CardView.setCardBackgroundColor(Color.parseColor(color))  // Establece el color de la nota que esta guardado en la base de datos

        holder.layout.title_tv.text = name
        holder.layout.subtitle_tv.text = subtitle

        holder.layout.note_button.setOnClickListener {
            val intent = Intent(activity, NotesViewActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("color", color)
            intent.putExtra("subtitle", subtitle)
            intent.putExtra("products", products_temp)
            intent.putExtra("amounts", amounts_temp)
            intent.putExtra("id", id2)
            activity.startActivity(intent)
        }
    }
}