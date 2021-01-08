package com.example.nutrisaapplication.ui.main.navigationDrawer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.login.LoginActivity
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_navigation_drawer.*


class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var drawerLayout:DrawerLayout
    private var fireBase: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        //toolbar.setNavigationIcon(R.drawable.ic_abeja_negra)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        fireBase= FirebaseAuth.getInstance()
        //val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)  supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menuFragment, R.id.visitaRapidaActivity, R.id.barraFragment, R.id.cajaFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        control()
    }

    private fun control(): Boolean {
        nav_view.setNavigationItemSelectedListener {
            item: MenuItem ->

            when(item.itemId) {
                R.id.menuFragment -> {
                    Toast.makeText(applicationContext, "seleccionaste menu principal", Toast.LENGTH_LONG)
                        .show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    // textView.text = "Cut Clicked."
                    true
                }
                R.id.visitaRapidaActivity -> {
                    Toast.makeText(applicationContext, "seleccionaste visitaRapida", Toast.LENGTH_LONG)
                        .show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    // textView.text = "Cut Clicked."
                    true
                }
                R.id.barraFragment -> {
                    Toast.makeText(applicationContext, "seleccionaste barra", Toast.LENGTH_LONG)
                        .show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    // textView.text = "Cut Clicked."
                    true
                }
                R.id.cerrarSesion-> {
                    fireBase?.signOut()
                    this.startActivity( Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                    true
                }
                else ->{
                    onBackPressed()
                    true
                }
            }
            }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }




    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}