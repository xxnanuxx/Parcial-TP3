package com.example.dogaplication.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.dogaplication.activities.MainActivity
import com.example.dogaplication.R
import com.example.dogaplication.database.AppDatabase
import com.example.dogaplication.database.PerritoDao
import com.example.dogaplication.database.UserDao
import com.example.dogaplication.entities.User
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    lateinit var btnLogin : Button
    lateinit var btnRegistrar : TextView
    lateinit var txtUser : TextInputEditText
    lateinit var txtPwd : EditText
    private  var db: AppDatabase? = null
    private var userDao: UserDao? = null
    private lateinit var view : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false)
        val sharedPreferences = requireContext().getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        btnLogin = view.findViewById<Button>(R.id.fragLogBtnLoginId)
        txtUser = view.findViewById<TextInputEditText>(R.id.fragLogTxtInUserId)
        txtPwd = view.findViewById<EditText>(R.id.fragLogTxtInPwdId)
        btnRegistrar = view.findViewById<TextInputEditText>(R.id.fragLogtxtRegistrarId)
        db = AppDatabase.getAppDataBase(view.context)
        userDao = db?.UserDao()

        btnLogin.setOnClickListener{

            val user = userDao?.loadUserByUsername(txtUser.text.toString())

            if (user != null){
                Log.i("pwdIn", txtPwd.text.toString())
                Log.i("pwdUser", user.contrasena)

                if (user.contrasena == txtPwd.text.toString()){
                    editor.putInt("id", user.id)
                    editor.putString("usuario", user.usuario)
                    editor.putString("nombre", user.nombre)
                    editor.putLong("telefono", user.telefono)
                    editor.putString("ubicacion", user.ubicacion)
                    editor.apply()
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "El usuario no existe", Toast.LENGTH_SHORT).show();
            }

        }

        btnRegistrar.setOnClickListener{
            val accion = LoginFragmentDirections.actionLoginFragmentToRegistrarFragment()
            view.findNavController().navigate(accion)
        }

        return view

    }

}