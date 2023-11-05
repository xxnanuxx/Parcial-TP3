package com.example.dogaplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogaplication.R
import com.example.dogaplication.adapters.PerritoListAdapter
import com.example.dogaplication.entities.Perrito
import com.example.dogaplication.listener.OnViewItemClickedListener
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), OnViewItemClickedListener {
    lateinit var v : View
    lateinit var reclistPerritos: RecyclerView
    lateinit var perritosListAdapter: PerritoListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
     var listaPerritos : MutableList<Perrito> = ArrayList()

    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false)

        reclistPerritos = v.findViewById(R.id.recPerritoList)

        for (i in 1..2) {
            listaPerritos.add(Perrito(5000,"Pedro","","", 5, 10, 0, 0, ""))
            listaPerritos.add(Perrito(5001,"Olivia","","", 2, 10,0, 0, ""))
            listaPerritos.add(Perrito(5002,"Martín","","", 9, 10,0, 0, ""))
            listaPerritos.add(Perrito(5003,"Matt","","", 9, 10,0,0, ""))


        }

        return v

    }

    override fun onStart() {
        super.onStart()


        requireActivity()

        reclistPerritos.setHasFixedSize(true) //fijo el ancho
        linearLayoutManager = LinearLayoutManager(context) // creo un manejador de vista lineal

        perritosListAdapter = PerritoListAdapter(listaPerritos, this) //

        reclistPerritos.layoutManager = linearLayoutManager // creo un adaptador

        reclistPerritos.adapter = perritosListAdapter

    }

    override fun onViewItemDetail(objectPerrito: Perrito) {
        val action = HomeFragmentDirections.actionHomeFragmentToPerritoDetails(objectPerrito)

        Log.i("accion", action.toString())
        this.findNavController().navigate(action)

        Snackbar.make(v,objectPerrito.nombre,Snackbar.LENGTH_SHORT).show()



    }


}