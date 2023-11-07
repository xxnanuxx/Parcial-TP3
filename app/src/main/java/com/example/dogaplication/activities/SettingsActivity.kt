package com.example.dogaplication.activities

import android.content.Context
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.dogaplication.R

class SettingsActivity : AppCompatActivity() {

    private val isNightModeOn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val swtchLightDarkMode = findViewById<Switch>(R.id.swtchDarkLightMode)

        //guardo el dark light mode en el shared preference.
        val sharedPreferences = getSharedPreferences("LIGHT_DARK_MODE", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val nightMode = sharedPreferences.getBoolean("NIGHT", true)

        //chequea en que modo esta y cambia el switch
        if(!nightMode){
            swtchLightDarkMode.isChecked = false
        }

        swtchLightDarkMode.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(!ischecked){
                //light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("NIGHT", false)
                editor.apply()
            }else{
                //dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("NIGHT", true)
                editor.apply()
            }

        }
    }

    /*class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }*/
}