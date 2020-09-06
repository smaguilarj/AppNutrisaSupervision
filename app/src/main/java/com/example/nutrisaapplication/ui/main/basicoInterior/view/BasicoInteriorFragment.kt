package com.example.nutrisaapplication.ui.main.basicoInterior.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.activity_basico_exterior.*

class BasicoInteriorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basico_interior, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOption()
    }

    private fun checkOption() {
        var status = "VISIBLE"
        var status2 = "VISIBLE"
        imageButton3.setOnClickListener {
            if (status == "VISIBLE") {
                tv_imgbutton3.visibility = View.VISIBLE
                status = ""
            } else {
                tv_imgbutton3.visibility = View.INVISIBLE
                status = "VISIBLE"
            }
        }
        imageButton4.setOnClickListener {
            if (status2 == "VISIBLE") {
                tv_imgbutton4.visibility = View.VISIBLE
                status2 = ""
            } else {
                tv_imgbutton4.visibility = View.INVISIBLE
                status2 = "VISIBLE"
            }
        }
    }
}