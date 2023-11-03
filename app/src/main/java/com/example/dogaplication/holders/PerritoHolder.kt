package com.example.dogaplication.holders
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogaplication.R

class PerritoHolder(v:View) : RecyclerView.ViewHolder(v){

    private var view: View

    init {
        this.view = v
    }

    fun setName(name: String) {
        val txt: TextView = view.findViewById(R.id.txt_name_item)
        txt.text = name
    }



    fun getCardLayout (): CardView {
        return view.findViewById(R.id.card_package_item)
    }

       fun setOrden(orden: Int){
        val txt: TextView = view.findViewById(R.id.txtOrden)
        txt.text = orden.toString()
    }


//
//        fun getImageView () : ImageView {
//            return view.findViewById(R.id.img_item)
//        }





}