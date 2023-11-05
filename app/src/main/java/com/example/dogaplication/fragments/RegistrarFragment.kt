package com.example.dogaplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.dogaplication.R
import com.example.dogaplication.activities.MainActivity
import com.example.dogaplication.database.UserDao
import com.example.dogaplication.database.appDatabase
import com.example.dogaplication.entities.User


class RegistrarFragment : Fragment() {
    private lateinit var view : View
    lateinit var btnReg : Button
    lateinit var editUser : EditText
    lateinit var editPwd : EditText
    lateinit var editName : EditText
    lateinit var editTel : EditText
    lateinit var editImgUrl : EditText
    private  var db: appDatabase? = null
    private var userDao: UserDao? = null

    var i : Int = 0

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

        return view
    }

    override fun onStart() {
        super.onStart()

        db = appDatabase.getAppDataBase(view.context)
        userDao = db?.UserDao()

        btnReg.setOnClickListener{
            Log.i("user",editUser.text.toString())
            userDao?.insertUser(User(i, editUser.text.toString(), editPwd.text.toString(), editName.text.toString(), (editTel.text.toString()).toLong(), editImgUrl.text.toString()))
            i += 1
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}