package com.example.nutrisaapplication.ui.main.salidaVisita.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
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
        btnResponsable.setOnClickListener {
            navigation.navigate(R.id.action_salidaVisitaRapidaFragment_to_signatureFragment)
            //startActivity(Intent(requireContext(),SignatureFragment::class.java))
        }
    }

}