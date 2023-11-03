package com.example.dogaplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dogaplication.activities.MainActivity
import com.example.dogaplication.R
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    lateinit var btnLogin : Button
    lateinit var textInput : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        btnLogin = view.findViewById<Button>(R.id.fragLogBtnLoginId)
        textInput = view.findViewById<TextInputEditText>(R.id.fragLogTxtInUserId)

        //binding.fragWelcBtnNextId.setOnClickListener{} VERIFICAR POR QUE NO ANDA

        btnLogin.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("usuario", textInput.text.toString())
            startActivity(intent)
        }
        return view

    }

}