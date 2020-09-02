package com.example.nutrisaapplication.ui.main.menuVisita

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.quickVisit.QuickVisitActivity
import kotlinx.android.synthetic.main.fragment_list.*


class MenuFragment : Fragment() {

    private val navigation by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FloatingActionButtonInicio.setOnClickListener {this.startActivity( Intent(requireContext(), QuickVisitActivity::class.java));FloatingActionButtonInicio.isEnabled=false  }
        topUpRowFloatingActionButtonBrandFachada.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_fachadaFragment);topUpRowFloatingActionButtonBrandFachada.isEnabled=false }
        floatingButtonPiso.setOnClickListener {  navigation.navigate(R.id.action_menuFragment_to_pisoFragment);floatingButtonPiso.isEnabled=false}
        floatingActionButtonBarra.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_barraFragment);floatingActionButtonBarra.isEnabled=false}
        floatingActionButtonCaja.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_cajaFragment);floatingActionButtonCaja.isEnabled=false }
        floatingActionButtonBodega.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_bodegaPizarraFragment);floatingActionButtonBodega.isEnabled=false }
        floatingButtonPlan.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_planDialogFragment);floatingButtonPlan.isEnabled=false }
        floatingActionButtonSalida.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_salidaVisitaRapidaFragment);floatingActionButtonSalida.isEnabled=false }
        floatingActionStatus.setOnClickListener { navigation.navigate(R.id.action_menuFragment_to_statusFragment) }
    }

}