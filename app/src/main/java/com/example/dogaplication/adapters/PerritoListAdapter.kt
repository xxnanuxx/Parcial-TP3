package com.example.dogaplication.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogaplication.R
import com.example.dogaplication.entities.Perrito
import com.example.dogaplication.holders.PerritoHolder
import com.example.dogaplication.listener.OnViewItemClickedListener

class PerritoListAdapter(

    private val perritosList: MutableList<Perrito>,
    private val onItemClick: OnViewItemClickedListener



) : RecyclerView.Adapter<PerritoHolder>() {

    override fun getItemCount() = perritosList.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_perrito, parent,false)

        return (PerritoHolder(view))
    }

    override fun onBindViewHolder(holder: PerritoHolder, position: Int) {
        val perrito = perritosList[position]
        holder.setName(TextUtils.concat(perrito.nombre," (", perrito.edad.toString(), ")").toString())
        holder.setOrden(position)
        holder.getCardLayout().setOnClickListener{
            onItemClick.onViewItemDetail(perrito)
        }
    }

}