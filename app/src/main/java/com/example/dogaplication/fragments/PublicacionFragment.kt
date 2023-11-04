package com.example.dogaplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.dogaplication.R
import com.example.dogaplication.entities.Breed
import com.example.dogaplication.entities.BreedsResponse
import com.example.dogaplication.services.ServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PublicacionFragment : Fragment() {
    private val breedsList = ArrayList<Breed>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar la vista primero
        val v = inflater.inflate(R.layout.fragment_publicacion, container, false)

        // Configurar AutoCompleteTextView después de inflar la vista
        val autoCompleteTextView = v.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autoCompleteTextView.threshold = 3 // Muestra sugerencias después de ingresar 3 caracteres

        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedBreed = breedsList[position]

            if (selectedBreed.subBreeds.isNotEmpty()) {
                val autoCompleteTextView = parent as AutoCompleteTextView
                val text = autoCompleteTextView.text.toString()
                val selectedText = selectedBreed.name
                val newText = if (text.isEmpty()) {
                    selectedText
                } else {
                    "$text ${selectedText}"
                }

                autoCompleteTextView.setText(newText)
                autoCompleteTextView.setSelection(newText.length) // Coloca el cursor al final del texto
                autoCompleteTextView.showDropDown() // Muestra las sugerencias de las subrazas
            } else {
                val selectedText = selectedBreed.name
                // Maneja la selección de sugerencias aquí (raza seleccionada)
            }
        }


        return v
    }

    override fun onStart() {
        super.onStart()
        val apiService = ServiceApiBuilder.create()
        val call = apiService.getBreeds()
        call.enqueue(object : Callback<BreedsResponse> {
            override fun onResponse(call: Call<BreedsResponse>, response: Response<BreedsResponse>) {
                if (response.isSuccessful) {
                    val breedsResponse = response.body()
                    if (breedsResponse != null) {
                        val breedMap = breedsResponse.message
                        if (breedMap != null) {
                            for ((breed, subBreeds) in breedMap) {
                                val breedObject = Breed(breed, subBreeds)
                                breedsList.add(breedObject)

                                if (subBreeds.isNotEmpty()) {
                                    subBreeds.forEach {
                                        val subBreedObject = Breed(it, emptyList())
                                        breedsList.add(subBreedObject)
                                    }
                                }
                            }

                            // Una vez que se ha construido breedsList, configura el adaptador
                            val autoCompleteTextView = view?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
                            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, breedsList.map { it.name })
                            autoCompleteTextView?.setAdapter(adapter)
                        }
                    }
                } else {
                    // Manejo de errores en caso de que la respuesta no sea exitosa.
                }
            }

            override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
                // Manejo de errores en caso de que ocurra una excepción.
            }
        })
    }
}

