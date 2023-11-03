package com.example.dogaplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.dogaplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)

        // Configurar BottomNavigationView con NavController
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        // Configurar el DrawerLayout
        setupDrawerLayout()
    }

    private fun setupDrawerLayout() {
        val navController = navHostFragment.navController

        // Vincular la navegación del Drawer con el gráfico de navegación
        navigationView.setupWithNavController(navController)

        // Configurar la AppBar para mostrar el icono del Drawer y actualizar el título
        //NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // Escuchar eventos de cambio de destino
        navController.addOnDestinationChangedListener { _, _, _ ->
            // Configurar el icono izquierdo de la AppBar como el del Drawer
            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburger)
        }
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.hamburger)
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return false
    }
}
