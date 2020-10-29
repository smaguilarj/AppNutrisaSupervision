package com.example.nutrisaapplication.ui.main.homeVisitaRapida

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.ui.main.inicioVisitaRapida.InicioVisitRapidaActivity
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_status.*


class MenuFragment : Fragment() {

    private val navigation by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FloatingActionButtonInicio.setOnClickListener {
            this.startActivity(
                Intent(
                    requireContext(),
                    InicioVisitRapidaActivity::class.java
                )
            );FloatingActionButtonInicio.isEnabled = false
        }
        topUpRowFloatingActionButtonBrandFachada.setOnClickListener {
            navigation.navigate(R.id.action_menuFragment_to_fachadaFragment);topUpRowFloatingActionButtonBrandFachada.isEnabled =
            false
        }
        floatingButtonPiso.setOnClickListener {
            navigation.navigate(R.id.action_menuFragment_to_pisoFragment);floatingButtonPiso.isEnabled =
            false
        }
        floatingActionButtonBarra.setOnClickListener {
            navigation.navigate(R.id.action_menuFragment_to_barraFragment);floatingActionButtonBarra.isEnabled =
            false
        }
        floatingActionButtonCaja.setOnClickListener {
            navigation.navigate(R.id.action_menuFragment_to_cajaFragment);floatingActionButtonCaja.isEnabled =
            false
        }
        floatingActionButtonBodega.setOnClickListener {
            navigation.navigate(R.id.action_menuFragment_to_bodegaPizarraFragment);floatingActionButtonBodega.isEnabled =
            false
        }
        floatingButtonPlan.setOnClickListener {
            navigation.navigate(R.id.action_menuFragment_to_planDialogFragment);floatingButtonPlan.isEnabled =
            false
        }
        floatingActionButtonSalida.setOnClickListener {
            navigation.navigate(R.id.action_menuFragment_to_salidaVisitaRapidaFragment);floatingActionButtonSalida.isEnabled =
            false
        }
        floatingActionStatus.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_statusFragment) }
        statusVisita()
    }

    override fun onResume() {
        super.onResume()
        statusVisita()
    }
    var visitaRapida = false
    private fun statusVisita() {

        val visita= SharedApp.prefs.visitaRapida
        val fachada = SharedApp.prefs.fachada
        val piso = SharedApp.prefs.piso
        val barra = SharedApp.prefs.barras
        val caja = SharedApp.prefs.caja
        val bodega = SharedApp.prefs.bodega
        val plan = SharedApp.prefs.plan



        if(visita){
            FloatingActionButtonInicio.isEnabled = false
            topUpRowFloatingActionButtonBrandFachada.isEnabled = true
            floatingButtonPiso.isEnabled = true
            floatingActionButtonBarra.isEnabled = true
            floatingActionButtonCaja.isEnabled =true
            floatingActionButtonBodega.isEnabled =true
            floatingButtonPlan.isEnabled =true
            floatingActionButtonSalida.isEnabled =true
            floatingActionStatus.isEnabled =true
            visitaRapida=true
        }else{
            FloatingActionButtonInicio.isEnabled = true
            topUpRowFloatingActionButtonBrandFachada.isEnabled = false
            floatingButtonPiso.isEnabled = false
            floatingActionButtonBarra.isEnabled = false
            floatingActionButtonCaja.isEnabled = false
            floatingActionButtonBodega.isEnabled =false
            floatingButtonPlan.isEnabled =false
            floatingActionButtonSalida.isEnabled =false
            floatingActionStatus.isEnabled =false
            visitaRapida=false
        }

        if(visitaRapida){
            topUpRowFloatingActionButtonBrandFachada.isEnabled = !fachada
            floatingButtonPiso.isEnabled = !piso
            floatingActionButtonBarra.isEnabled = !barra
            floatingActionButtonCaja.isEnabled = !caja
            floatingActionButtonBodega.isEnabled = !bodega
            floatingButtonPlan.isEnabled = !plan
        }

    }
}