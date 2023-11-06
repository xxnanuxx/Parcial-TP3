package com.example.dogaplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogaplication.R
import com.example.dogaplication.adapters.PerritoListAdapter
import com.example.dogaplication.database.AppDatabase
import com.example.dogaplication.database.PerritoDao
import com.example.dogaplication.entities.Perrito
import com.example.dogaplication.listener.OnViewItemClickedListener
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), OnViewItemClickedListener {
    lateinit var v : View
    lateinit var reclistPerritos: RecyclerView
    lateinit var perritosListAdapter: PerritoListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    var listaPerritos : MutableList<Perrito?>? = ArrayList()
    private  var db: AppDatabase? = null
    private var perritoDao: PerritoDao? = null

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

        return v

    }

    override fun onStart() {
        super.onStart()
        db = AppDatabase.getAppDataBase(v.context)
        perritoDao = db?.PerritoDao()
        listaPerritos = perritoDao?.loadAllPerritos()

        requireActivity()
        reclistPerritos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)

        perritosListAdapter = PerritoListAdapter(listaPerritos, this)

        reclistPerritos.layoutManager = linearLayoutManager
        reclistPerritos.adapter = perritosListAdapter

    }

    override fun onViewItemDetail(objectPerrito: Perrito) {
        val action = HomeFragmentDirections.actionHomeFragmentToPerritoDetails(objectPerrito)

        this.findNavController().navigate(action)

        Snackbar.make(v,objectPerrito.nombre,Snackbar.LENGTH_SHORT).show()



    }


}