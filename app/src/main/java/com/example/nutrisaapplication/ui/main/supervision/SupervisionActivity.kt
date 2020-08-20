package com.example.nutrisaapplication.ui.main.supervision

import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.home.HomeActivity
import com.example.nutrisaapplication.ui.main.home_regional.view.HomeRegionalActivity
import com.example.nutrisaapplication.ui.main.menuVisita.MenuActivity
import kotlinx.android.synthetic.main.activity_supervision.*

class SupervisionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervision)
        imgVisitaRapida.setOnClickListener {
            this.startActivity( Intent(this, MenuActivity::class.java))
        }

        imgViewOperaciones.setOnClickListener {
            this.startActivity( Intent(this, HomeActivity::class.java))
        }
        imgViewRegional.setOnClickListener {
           // this.changeActivity(HomeRegionalActivity::class.java)
            this.startActivity( Intent(this, HomeRegionalActivity::class.java))
        }
    }

    override fun onBackPressed() = Unit
}