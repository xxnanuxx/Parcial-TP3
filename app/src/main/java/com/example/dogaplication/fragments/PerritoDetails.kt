package com.example.dogaplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.dogaplication.R
import com.example.dogaplication.database.AppDatabase
import com.example.dogaplication.database.UserPerritoFavDao
import com.example.dogaplication.entities.Perrito
import com.example.dogaplication.entities.UserFavPerritoCrossRef
import com.squareup.picasso.Picasso

class PerritoDetails : Fragment() {
    lateinit var v: View
    lateinit var nombre: TextView
    lateinit var edad: TextView
    lateinit var peso: TextView
    lateinit var macho: TextView
    lateinit var dueno: TextView
    lateinit var ubicacion: TextView
    lateinit var imgView : ImageView
    lateinit var btnFav: ImageButton
    private var userPerritoFavDao : UserPerritoFavDao? = null
    private var db: AppDatabase? = null
    private var esFavorito = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        v = inflater.inflate(R.layout.fragment_perrito_details, container, false)
        nombre = v.findViewById(R.id.txtNombre)
        edad = v.findViewById(R.id.txtEdad)
        peso = v.findViewById(R.id.txtPeso)
        macho = v.findViewById(R.id.txtMacho)
        dueno = v.findViewById(R.id.txtDueno)
        ubicacion = v.findViewById(R.id.txtUbicacion)
        imgView = v.findViewById(R.id.fragPDetailImgViewId)
        btnFav = v.findViewById(R.id.btnFavId)

        return v


    }

    override fun onStart() {
        super.onStart()

        arguments?.let {
            val perrito = PerritoDetailsArgs.fromBundle(it).objectPerrito
            db = AppDatabase.getAppDataBase(v.context)
            userPerritoFavDao = db?.UserPerritoFavDao()
            val sharedPreferences = requireContext().getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
            val usuarioId = sharedPreferences.getInt("id", 0)

            nombre.text = perrito?.nombre
            edad.text =  perrito?.edad.toString()
            peso.text = perrito?.peso.toString()
            macho.text = if (perrito?.macho == 1.toByte()) "Macho" else "Hembra"
            dueno.text = perrito?.dueno
            ubicacion.text = perrito?.ubicacion

            val objeto = userPerritoFavDao?.getFavorite(usuarioId, perrito?.id)
            if (objeto != null) {
                esFavorito = true
                btnFav.setBackgroundColor((R.color.colorTintRed.toInt()))
            }

            Picasso.get().load(perrito?.imagen).into(imgView);

            btnFav.setOnClickListener{

                if(esFavorito){
                    esFavorito = false
                    userPerritoFavDao?.deleteUserFavPerritoCrossRef(usuarioId, perrito.id)
                    btnFav.setBackgroundColor((R.color.white.toInt()))

                }else{
                    esFavorito = true
                    val favoritoUser = UserFavPerritoCrossRef(usuarioId, perrito.id)
                    userPerritoFavDao?.insertUserFavPerritoCrossRef(favoritoUser)
                    btnFav.setBackgroundColor(R.color.colorTintRed.toInt())
                }
            }
        }

    }

}