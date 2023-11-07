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
import android.widget.Toast
import com.example.dogaplication.R
import com.example.dogaplication.activities.MainActivity
import com.example.dogaplication.database.UserDao
import com.example.dogaplication.database.AppDatabase
import com.example.dogaplication.entities.User


class RegistrarFragment : Fragment() {
    private lateinit var view : View
    lateinit var btnReg : Button
    lateinit var editUser : EditText
    lateinit var editPwd : EditText
    lateinit var editName : EditText
    lateinit var editTel : EditText
    lateinit var editImgUrl : EditText
    lateinit var editUbi : EditText
    private  var db: AppDatabase? = null
    private var userDao: UserDao? = null

    var i : Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = inflater.inflate(R.layout.fragment_registrar, container, false)
        btnReg = view.findViewById(R.id.fragRegBtnRegId)
        editUser = view.findViewById(R.id.fragRegTxtInUserId)
        editPwd = view.findViewById(R.id.fragRegTxtInPwdId)
        editName = view.findViewById(R.id.fragRegTxtInNameId)
        editTel = view.findViewById(R.id.fragRegTxtInTelId)
        editImgUrl = view.findViewById(R.id.fragRegTxtInImgId)
        editUbi = view.findViewById(R.id.fragRegTxtInUbiId)

        return view
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getAppDataBase(view.context)
        userDao = db?.UserDao()

        btnReg.setOnClickListener{
            val sharedPreferences = requireContext().getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            db = AppDatabase.getAppDataBase(view.context)
            userDao = db?.UserDao()
            val user = userDao?.loadUserByUsername(editUser.text.toString())
            i = userDao?.countUsers()


            if (user == null) {
                userDao?.insertUser(User(i, editUser.text.toString(), editName.text.toString(),editPwd.text.toString(), editTel.text.toString(), editImgUrl.text.toString(), editUbi.text.toString()))
                editor.putString("usuario", editUser.text.toString())
                i?.let { it1 -> editor.putInt("id", it1) }
                editor.putString("nombre", editName.text.toString())
                editor.putString("telefono", editTel.text.toString())
                editor.putString("ubicacion", editTel.text.toString())
                editor.apply()
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            }
        }
    }

}