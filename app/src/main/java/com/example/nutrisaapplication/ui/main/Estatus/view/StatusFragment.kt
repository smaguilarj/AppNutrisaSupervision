package com.example.nutrisaapplication.ui.main.Estatus.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.activity_basico_exterior.*
import kotlinx.android.synthetic.main.fragment_status.*

class StatusFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOption()
    }

    private fun checkOption() {
        var status="VISIBLE"

        imageButtonInf.setOnClickListener {
            if(status=="VISIBLE"){
                textViewInf.visibility= View.VISIBLE
                status=""
            }else{
                textViewInf.visibility= View.INVISIBLE
                status="VISIBLE"
            }
        }
    }
}