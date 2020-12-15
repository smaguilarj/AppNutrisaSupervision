package com.example.nutrisaapplication.ui.main.kpi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.fragment_kpi2.*

class KpiFragment2 : Fragment() {
    val navigation by lazy {
        findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kpi2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_enviarKpi2.setOnClickListener {
            navigation.navigate(R.id.action_kpiFragment2_to_visitToStoreFragment)
        }
    }
}