package com.example.nutrisaapplication.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.home_regional.view.HomeRegionalActivity
import com.example.nutrisaapplication.ui.main.view.VisitaRapidaActivity
import com.example.nutrisaapplication.ui.main.visitaOperaciones.BasicoExteriorActivity
import kotlinx.android.synthetic.main.activity_supervision.*
import kotlinx.android.synthetic.main.fragment_blank.*

/**
 * A simple [Fragment] subclass.
 */
class VisitToStoreFragment : Fragment() {

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
            (activity as BaseActivity).changeActivity(VisitaRapidaActivity::class.java)
        }

        imgInicio.setOnClickListener {
            (activity as BaseActivity).changeActivity(VisitaRapidaActivity::class.java)
        }

        btnBasico.setOnClickListener {
            (activity as BaseActivity).changeActivity(BasicoExteriorActivity::class.java)
        }
        linearLayoutBasicos.setOnClickListener {
            (activity as BaseActivity).changeActivity(BasicoExteriorActivity::class.java)
        }


    }

}
