package com.example.dogaplication.holders
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogaplication.R
import com.squareup.picasso.Picasso

class PerritoHolder(v:View) : RecyclerView.ViewHolder(v){

    private var view: View

    init {
        this.view = v
    }

    fun setName(name: String?) {
        val txt: TextView = view.findViewById(R.id.fragItemPerritoTxtNombre)
        txt.text = name
    }

    fun setRaza(raza :String?){
        val txt: TextView = view.findViewById(R.id.fragItemPerritoTxtRaza)
        txt.text = raza
    }

    fun setSubRaza(subRaza :String?){
        val txt: TextView = view.findViewById(R.id.fragItemPerritoTxtSubraza)
        txt.text = subRaza
    }

    fun setEdad( edad : Int?){
        val txt : TextView = view.findViewById(R.id.fragItemPerritoTxtEdad)
        txt.text = edad.toString()
    }
    fun setSexo(sexo :String?){
        val txt: TextView = view.findViewById(R.id.fragItemPerritoTxtSexo)
        txt.text = sexo
    }

    fun setUrlImagen(urlImagen : String?){
        val imgView : ImageView = view.findViewById(R.id.fragItemPerritoImgViewFondoTarjeta)
        Picasso.get().load(urlImagen).into(imgView)
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.card_package_item)
    }


}