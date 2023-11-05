package com.example.dogaplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dogaplication.R
import com.example.dogaplication.entities.Perrito

class PerritoDetails : Fragment() {
    lateinit var v: View
    lateinit var info: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        v = inflater.inflate(R.layout.fragment_perrito_details, container, false)
        info = v.findViewById(R.id.txtInfo)

        return v


    }

    override fun onStart() {
        super.onStart()

        val perrito: Perrito? = arguments?.getParcelable("perrito")
        info.text = perrito?.nombre
        //  arguments?.let {
        //  val perrito = PerritoDetailsArgs.fromBundle(it).perrito
        //   info.text = perrito.nombre
        //}

    }

}