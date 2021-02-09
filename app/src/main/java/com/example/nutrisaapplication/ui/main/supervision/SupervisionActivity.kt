package com.example.nutrisaapplication.ui.main.supervision

import android.content.Intent
import android.os.Bundle
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.homeVisitaLarga.HomeActivity
import com.example.nutrisaapplication.ui.main.home_regional.view.HomeRegionalActivity
import com.example.nutrisaapplication.ui.main.homeVisitaRapida.MenuActivity
import com.example.nutrisaapplication.ui.main.login.LoginActivity
import com.example.nutrisaapplication.ui.main.navigationDrawer.NavigationDrawerActivity
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_supervision.*
import java.util.jar.Manifest

class SupervisionActivity : BaseActivity() {

    private var fireBase:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervision)
        //Dexter.withActivity(this).withPermission(android.Manifest.permission.CAMERA).withListener(this).check()

        fireBase= FirebaseAuth.getInstance()
        imgVisitaRapida.setOnClickListener {
            /*this.startActivity( Intent(this, MenuActivity::class.java))*/
            this.startActivity( Intent(this, NavigationDrawerActivity::class.java))
        }

        imgViewOperaciones.setOnClickListener {
            this.startActivity( Intent(this, HomeActivity::class.java))
        }
        /*imgViewRegional.setOnClickListener {
           // this.changeActivity(HomeRegionalActivity::class.java)
            this.startActivity( Intent(this, HomeRegionalActivity::class.java))
        }*/
        buttonCerrar.setOnClickListener {
            fireBase?.signOut()
            this.startActivity( Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }

    override fun onBackPressed() = Unit

}