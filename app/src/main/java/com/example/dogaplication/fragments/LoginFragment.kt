package com.example.dogaplication.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.dogaplication.activities.MainActivity
import com.example.dogaplication.R
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    lateinit var btnLogin : Button
    lateinit var btnRegistrar : TextView
    lateinit var txtUser : TextInputEditText
    lateinit var txtPwd : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val sharedPreferences = requireContext().getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        btnLogin = view.findViewById<Button>(R.id.fragLogBtnLoginId)
        txtUser = view.findViewById<TextInputEditText>(R.id.fragLogTxtInUserId)
        txtPwd = view.findViewById<EditText>(R.id.fragLogTxtInPwdId)
        btnRegistrar = view.findViewById<TextInputEditText>(R.id.fragLogtxtRegistrarId)

        btnLogin.setOnClickListener{

            editor.putString("usuario", txtUser.text.toString())
            editor.putString("telefono", txtPwd.text.toString())
            editor.apply()
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("usuario", txtUser.text.toString())
            startActivity(intent)
        }

        btnRegistrar.setOnClickListener{
            val accion = LoginFragmentDirections.actionLoginFragmentToRegistrarFragment()
            view.findNavController().navigate(accion)
        }

        return view

    }

}