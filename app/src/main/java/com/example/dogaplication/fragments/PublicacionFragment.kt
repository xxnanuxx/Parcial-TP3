package com.example.dogaplication.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.dogaplication.R
import com.example.dogaplication.entities.Breed
import com.example.dogaplication.entities.BreedsResponse
import com.example.dogaplication.services.ServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.dogaplication.database.PerritoDao
import com.example.dogaplication.database.AppDatabase
import com.example.dogaplication.entities.Perrito
import com.google.android.material.textfield.TextInputLayout

class PublicacionFragment : Fragment() {

    private val breedsList = ArrayList<Breed>()
    private lateinit var nombre: EditText
    private lateinit var edad: EditText
    private lateinit var peso: EditText
    private lateinit var duenio: String
    private lateinit var telefono: String
    private lateinit var macho: ToggleButton
    private lateinit var descripcion: EditText
    private lateinit var ubicacion: String
    private lateinit var imagen: EditText
    private lateinit var raza: AutoCompleteTextView
    private lateinit var btnPublicar: Button
    private var db: AppDatabase? = null
    private var perritoDao: PerritoDao? = null
    private lateinit var v: View
    private val breedNames: MutableList<String> = mutableListOf()

    var i: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_publicacion, container, false)
        val sharedPreferences = requireActivity().getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
        val usuario = sharedPreferences.getString("usuario", "").toString()
        val telDuenio = sharedPreferences.getString("telefono", "").toString()
        ubicacion = sharedPreferences.getString("ubicacion", "").toString()
        val autoCompleteTextView = v.findViewById<AutoCompleteTextView>(R.id.fragPubAutoCompleteTextView)
        autoCompleteTextView.threshold = 2 // Muestra sugerencias después de ingresar 2 caracteres
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, breedNames)
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.threshold = 2
        val textInputLayout = v.findViewById<TextInputLayout>(R.id.textInputLayout)
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                val selectedBreed = editable.toString()
                if (!isValidBreed(selectedBreed)) {
                    textInputLayout.error = "Raza no válida"
                } else {
                    textInputLayout.error = null
                }
            }
        })

        raza = autoCompleteTextView
        nombre = v.findViewById(R.id.fragPubEditTxtNombre)
        edad = v.findViewById(R.id.fragPubEditTxtEdad)
        peso = v.findViewById(R.id.fragPubEditTxtPeso)
        macho = v.findViewById(R.id.fragPubtoggleButton)
        descripcion = v.findViewById(R.id.fragPubEditTxtDescrip)
        imagen = v.findViewById(R.id.fragPubEditTxtUrlImages)
        duenio = usuario
        telefono = telDuenio
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
                        breedsList.clear()
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
                        //Log.d("BreedsList", "Size: ${breedsList.size}")
                        //Log.d("BreedsList", "Contents: $breedsList")
                        val autoCompleteTextView =
                            view?.findViewById<AutoCompleteTextView>(R.id.fragPubAutoCompleteTextView)

                        breedNames.clear()
                        for (breed in breedsList) {
                            if (breed.subBreeds.isNotEmpty()) {
                                for (subBreed in breed.subBreeds) {
                                    breedNames.add("${breed.name} $subBreed")
                                }
                            } else {
                                breedNames.add(breed.name)
                            }
                        }
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            breedNames
                        )
                        autoCompleteTextView?.setAdapter(adapter)
                    }
                } else {
                    // error
                }
            }

            override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
                Log.e("API Error", "Error en la llamada a la API", t)
            }
        })

        db = AppDatabase.getAppDataBase(v.context)
        perritoDao = db?.PerritoDao()
        i = perritoDao?.countPerrito()

        btnPublicar.setOnClickListener {
            val machoByte: Byte = if (macho.isChecked) 1.toByte() else 0.toByte()
            if (isValidBreed(raza.text.toString())) {
                perritoDao?.insertPerrito(
                    Perrito(
                        i, nombre.text.toString(),
                        raza.text.toString(),
                        "",
                        Integer.parseInt(edad.text.toString()),
                        Integer.parseInt(peso.text.toString()),
                        machoByte,
                        0.toByte(),
                        imagen.text.toString(),
                        duenio,
                        telefono,
                        ubicacion
                    )
                )
                val accion = PublicacionFragmentDirections.actionPublicacionFragmentToHomeFragment()
                v.findNavController().navigate(accion)
            } else {
                raza.error = "Raza no válida"
            }
        }
    }
    private fun isValidBreed(selectedBreed: String): Boolean {
        val normalizedSelectedBreed = selectedBreed.trim().lowercase()
        for (breedName in breedNames) {
            val normalizedBreedName = breedName.trim().lowercase()
            if (normalizedBreedName == normalizedSelectedBreed) {
                return true
            }
        }
        return false
    }
}
