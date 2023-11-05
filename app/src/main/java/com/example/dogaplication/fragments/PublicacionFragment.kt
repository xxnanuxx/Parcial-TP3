package com.example.dogaplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import com.example.dogaplication.R
import com.example.dogaplication.entities.Breed
import com.example.dogaplication.entities.BreedsResponse
import com.example.dogaplication.services.ServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
import com.example.dogaplication.database.PerritoDao
import com.example.dogaplication.database.appDatabase
import com.example.dogaplication.entities.Perrito


class PublicacionFragment : Fragment() {

    private val breedsList = ArrayList<Breed>()
    private lateinit  var nombre : EditText
    private lateinit  var edad : EditText
    private lateinit  var peso : EditText
    private lateinit  var duenio : EditText
    private lateinit  var macho : ToggleButton
    private lateinit var descripcion : EditText
    private lateinit  var ubicacion : EditText
    private lateinit var imagen : EditText
    private lateinit var raza : AutoCompleteTextView
    private lateinit var btnPublicar : Button
    private  var db: appDatabase? = null
    private var perritoDao: PerritoDao? = null
    private lateinit var v : View

    var i : Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar la vista primero
        v = inflater.inflate(R.layout.fragment_publicacion, container, false)

        // Configurar AutoCompleteTextView después de inflar la vista
        val autoCompleteTextView = v.findViewById<AutoCompleteTextView>(R.id.fragPubAutoCompleteTextView)
        autoCompleteTextView.threshold = 2 // Muestra sugerencias después de ingresar 2 caracteres

        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedBreed = breedsList[position]

            if (selectedBreed.subBreeds.isNotEmpty()) {
                //val autoCompleteTextView = parent as AutoCompleteTextView
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
                //val selectedText = selectedBreed.name
                // Maneja la selección de sugerencias aquí (raza seleccionada)
            }
        }

        raza = autoCompleteTextView
        nombre = v.findViewById(R.id.fragPubEditTxtNombre)
        edad = v.findViewById(R.id.fragPubEditTxtEdad)
        peso = v.findViewById(R.id.fragPubEditTxtPeso)
        duenio = v.findViewById(R.id.fragPubEditTxtDuenio)
        macho = v.findViewById(R.id.fragPubtoggleButton)
        descripcion = v.findViewById(R.id.fragPubEditTxtDescrip)
        ubicacion = v.findViewById(R.id.fragPubEditTxtUbicacion)
        imagen = v.findViewById(R.id.fragPubEditTxtUrlImages)
        btnPublicar = v.findViewById(R.id.fragPubBtnPublicar)

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
                            Log.i("Fede","Funca")
                            // Una vez que se ha construido breedsList, configura el adaptador
                            val autoCompleteTextView = view?.findViewById<AutoCompleteTextView>(R.id.fragPubAutoCompleteTextView)
                            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, breedsList.map { it.name })
                            autoCompleteTextView?.setAdapter(adapter)

                    }
                } else {
                    // Manejo de errores en caso de que la respuesta no sea exitosa.
                    Log.e("FedeError","Error Federico")
                }
            }

            override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
                // Manejo de errores en caso de que ocurra una excepción.
            }
        })

        // mas componentes:


        db = appDatabase.getAppDataBase(v.context)
        perritoDao = db?.PerritoDao()
        i = perritoDao?.countPerrito()

        btnPublicar.setOnClickListener {
            //Se pasa el toggle button de boolean a byte. Si es hembra es 0 y si es macho es 1
            val machoByte: Byte = if (macho.isChecked) 1.toByte() else 0.toByte()
            perritoDao?.insertPerrito(
                Perrito(i, nombre.text.toString(),
                raza.text.toString(),
                "" ,
                Integer.parseInt(edad.text.toString()),
                Integer.parseInt(peso.text.toString()) ,
                machoByte,
                0.toByte(),
                imagen.text.toString() ))

            db?.openHelper?.writableDatabase?.close()


        //Log.i("Perrito",nombre.text.toString())

        }

    }
}

