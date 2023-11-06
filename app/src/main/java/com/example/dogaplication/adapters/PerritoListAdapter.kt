package com.example.dogaplication.adapters

//import android.text.TextUtils
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.dogaplication.R
import com.example.dogaplication.entities.Perrito
import com.example.dogaplication.holders.PerritoHolder
import com.example.dogaplication.listener.OnViewItemClickedListener

class PerritoListAdapter(

    private val perritosList: MutableList<Perrito?>?,
    private val onItemClick: OnViewItemClickedListener,

) : RecyclerView.Adapter<PerritoHolder>() {


    private var perritoCount : Int = 0

    override fun getItemCount() : Int {
        if (perritosList != null){
            perritoCount = perritosList.size
        }
        return perritoCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_perrito, parent,false)

        return (PerritoHolder(view))
    }

    override fun onBindViewHolder(holder: PerritoHolder, position: Int) {
        //val sharedPreferences = requireContext(context).getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
        //val ubicacion = sharedPreferences.getString("ubicacion", "").toString()
        //holder.setUbicacion(ubicacion)

        val perrito = perritosList?.get(position)
        holder.setName(perrito?.nombre.toString())
        holder.setEdad(perrito?.edad)
        val sexo: String = if (perrito?.macho == 1.toByte()) "Macho" else "Hembra"
        holder.setSexo(sexo)
        holder.setRaza(perrito?.raza)
        holder.setSubRaza(perrito?.subRaza)
        holder.setUrlImagen(perrito?.imagen)

        holder.getCardLayout().setOnClickListener{
            if (perrito != null) {
                onItemClick.onViewItemDetail(perrito)
            }
        }
    }

}