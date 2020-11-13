package com.example.nutrisaapplication.ui.main.salidaVisita.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import kotlinx.android.synthetic.main.fragment_salida_visita_rapida.*

class SalidaVisitaRapidaFragment : Fragment() {

    val navigation by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salida_visita_rapida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_firma_evaluador.setOnClickListener {
            navigation.navigate(R.id.action_salidaVisitaRapidaFragment_to_signatureFragment)
            //startActivity(Intent(requireContext(),SignatureFragment::class.java))
        }
        edtResponsable.setOnClickListener {
            navigation.navigate(R.id.action_salidaVisitaRapidaFragment_to_signatureFragment)
            //startActivity(Intent(requireContext(),SignatureFragment::class.java))
        }
        btn_salida_rapida.setOnClickListener {
            SharedApp.prefs.visitaRapida = false
            SharedApp.prefs.fachada=false
            SharedApp.prefs.piso=false
            SharedApp.prefs.barras=false
            SharedApp.prefs.caja=false
            SharedApp.prefs.bodega=false
            SharedApp.prefs.plan=false
            SharedApp.prefs.borrarLista=false
            activity?.onBackPressed()
        }
    }

}