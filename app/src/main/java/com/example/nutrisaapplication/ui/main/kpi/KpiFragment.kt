package com.example.nutrisaapplication.ui.main.kpi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.fragment_kpi.*

class KpiFragment : Fragment() {
    val navigation by lazy {
        findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kpi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_enviarKpi.setOnClickListener {
           navigation.navigate(R.id.action_kpiFragment_to_kpiFragment2)
        }
    }

}