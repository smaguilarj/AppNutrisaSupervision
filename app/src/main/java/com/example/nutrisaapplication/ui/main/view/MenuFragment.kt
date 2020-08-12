package com.example.nutrisaapplication.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.piso.view.PisoActivity
import kotlinx.android.synthetic.main.fragment_list.*


class MenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topUpRowFloatingActionButtonBrandFachada.setOnClickListener { this.startActivity( Intent(requireContext(), FachadaActivity::class.java)) }
        floatingButtonPiso.setOnClickListener {  this.startActivity( Intent(requireContext(), PisoActivity::class.java)) }
    }

}