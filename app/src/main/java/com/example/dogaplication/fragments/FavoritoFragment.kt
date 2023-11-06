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
import com.example.dogaplication.database.UserDao
import com.example.dogaplication.entities.Perrito
import com.google.android.material.snackbar.Snackbar
import com.example.dogaplication.listener.OnViewItemClickedListener

class FavoritoFragment : Fragment(), OnViewItemClickedListener {

    lateinit var v : View
    lateinit var reclistPerritos: RecyclerView
    lateinit var perritosListAdapter: PerritoListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    var listaPerritos : MutableList<Perrito?>? = ArrayList()
    private  var db: AppDatabase? = null
    private var userDao: UserDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorito, container, false)
    }

    override fun onStart() {
        super.onStart()
        db = AppDatabase.getAppDataBase(v.context)
        userDao = db?.UserDao()
        //listaPerritos = userDao?.getFavoritos()

        requireActivity()
        reclistPerritos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)

        perritosListAdapter = PerritoListAdapter(listaPerritos, this)

        reclistPerritos.layoutManager = linearLayoutManager
        reclistPerritos.adapter = perritosListAdapter

    }
    override fun onViewItemDetail(objectPerrito: Perrito) {
        val action = FavoritoFragmentDirections.actionFavoritoFragmentToPerritoDetails(objectPerrito)
        this.findNavController().navigate(action)

        Snackbar.make(v,objectPerrito.nombre, Snackbar.LENGTH_SHORT).show()



    }

}