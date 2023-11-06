package com.example.dogaplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dogaplication.R
import com.example.dogaplication.entities.Perrito
import com.squareup.picasso.Picasso

class PerritoDetails : Fragment() {
    lateinit var v: View
    lateinit var info: TextView
    lateinit var imgView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        v = inflater.inflate(R.layout.fragment_perrito_details, container, false)
        info = v.findViewById(R.id.txtInfo)
        imgView = v.findViewById(R.id.fragPDetailImgViewId)

        return v


    }

    override fun onStart() {
        super.onStart()

        arguments?.let {
          val perrito = PerritoDetailsArgs.fromBundle(it).objectPerrito
          info.text = perrito?.nombre
            Picasso.get().load(perrito?.imagen).into(imgView);
        }

    }

}