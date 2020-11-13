package com.example.nutrisaapplication.ui.main.homeVisitaRapida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.model.PlanTrabajoModel
import com.example.nutrisaapplication.ui.main.plan_trabajo.viewmodel.PlanViewModel

class MenuActivity : AppCompatActivity() {

    private val navigation by lazy { Navigation.findNavController(this,R.id.nav_host_fragment) }
    //private lateinit var viewModel: ViewModel
    val menuViewModel by lazy {
        ViewModelProvider(this).get(PlanViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initObserver()
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
        else if(navigation.currentDestination?.id==R.id.planTrabajoFragment){
            //navigation.navigate(R.id.action_barraFragment_to_menuFragment)
        }
        else if(navigation.currentDestination?.id==R.id.statusFragment){
            navigation.navigate(R.id.action_statusFragment_to_menuFragment)
        }
        else if(navigation.currentDestination?.id==R.id.menuFragment){
            finish()
        }
        else if(navigation.currentDestination?.id==R.id.salidaVisitaRapidaFragment){
            navigation.navigate(R.id.action_salidaVisitaRapidaFragment_to_menuFragment)
        }
        else{
            super.onBackPressed()
        }
    }

    /*val planObserver= Observer<List<PlanTrabajoModel>>{
        Log.d("plan",it.toString())
    }*/
    val planObserver= Observer<MutableList<PlanTrabajoModel>>{
        Log.d("list",it.toString())
    }
    //menuViewModel.getListLiveData().observe(this,planObserver)
    fun initObserver() {
        menuViewModel.listData.observe(this, planObserver)
    }
}