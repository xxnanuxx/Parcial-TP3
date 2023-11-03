package com.example.dogaplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.dogaplication.R
import com.example.dogaplication.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding : FragmentWelcomeBinding
    lateinit var btnNext : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        binding = FragmentWelcomeBinding.inflate(layoutInflater)

        btnNext = view.findViewById<Button>(R.id.fragWelcBtnNextId)

        //binding.fragWelcBtnNextId.setOnClickListener{} VERIFICAR POR QUE NO ANDA

        btnNext.setOnClickListener{
            val accion = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
            view.findNavController().navigate(accion)
        }

        return view
    }

}