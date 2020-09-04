package com.example.nutrisaapplication.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.basicoInterior.view.BasicoInteriorFragment
import com.example.nutrisaapplication.ui.main.visitaLarga.VisitaLargaActivity
import com.example.nutrisaapplication.ui.main.basicoExterior.BasicoExteriorActivity
import kotlinx.android.synthetic.main.fragment_blank.*

/**
 * A simple [Fragment] subclass.
 */
class VisitToStoreFragment : Fragment() {

    private val navigation by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayout.setOnClickListener {
            (activity as BaseActivity).changeActivity(VisitaLargaActivity::class.java)
        }

        imgInicio.setOnClickListener {
            (activity as BaseActivity).changeActivity(VisitaLargaActivity::class.java)
        }

        btnBasico.setOnClickListener {
            (activity as BaseActivity).changeActivity(BasicoExteriorActivity::class.java)
        }
        linearLayoutBasicos.setOnClickListener {
            (activity as BaseActivity).changeActivity(BasicoExteriorActivity::class.java)
        }

        linearBasicoInterior.setOnClickListener {
            navigation.navigate(R.id.action_visitToStoreFragment_to_basicoInteriorFragment)
        }
        tv_basico_interior.setOnClickListener {
            navigation.navigate(R.id.action_visitToStoreFragment_to_basicoInteriorFragment)
        }
    }

}
