package com.example.nutrisaapplication.ui.main.menuVisita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.nutrisaapplication.R

class MenuActivity : AppCompatActivity() {

    private val navigation by lazy { Navigation.findNavController(this,R.id.nav_host_fragment) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    override fun onBackPressed() {

        if (navigation.currentDestination?.id==R.id.fachadaFragment){
            super.onBackPressed()
        }else if (navigation.currentDestination?.id==R.id.pisoFragment){
            //finish()
            navigation.navigate(R.id.action_pisoFragment_to_menuFragment)
        }else if(navigation.currentDestination?.id==R.id.barraFragment){
            //finish()
            navigation.navigate(R.id.action_barraFragment_to_menuFragment)
        }
        else if(navigation.currentDestination?.id==R.id.cajaFragment){
            navigation.navigate(R.id.action_cajaFragment_to_menuFragment)
        }
        else if(navigation.currentDestination?.id==R.id.barraFragment){
            navigation.navigate(R.id.action_barraFragment_to_menuFragment)
        }
        else{
            super.onBackPressed()
        }
    }
}