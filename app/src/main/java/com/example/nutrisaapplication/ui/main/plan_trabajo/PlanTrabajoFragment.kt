package com.example.nutrisaapplication.ui.main.plan_trabajo

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.Estatus.view.StatusFragment
import kotlinx.android.synthetic.main.fragment_plan_trabajo.*
import java.util.*

class PlanTrabajoFragment : Fragment() {

    private lateinit var table: TableLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_trabajo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        table = view.findViewById<View>(R.id.simpleTableLayout) as TableLayout
        CreateTable()
        buttonEnviar.setOnClickListener {
            SharedApp.prefs.plan=true
            activity?.onBackPressed()
        }
    }
    private fun CreateTable()
    {
        val temps= doubleArrayOf(1.0)
        val rowHead = LayoutInflater.from(context).inflate(R.layout.attrib_row, null) as TableRow
        (rowHead.findViewById<View>(R.id.attrib_value) as TextView).text=(" ")
        (rowHead.findViewById<View>(R.id.attrib_name) as TextView).text=(" ")
        (rowHead.findViewById<View>(R.id.attrib_name2) as TextView).text=(" Tienda ")
        (rowHead.findViewById<View>(R.id.attrib_value2) as TextView).text=(" Gerente de turno ")
        (rowHead.findViewById<View>(R.id.attrib_name3) as TextView).text=(" Acciones a tomar ")
        (rowHead.findViewById<View>(R.id.attrib_value3) as TextView).text=(" Responsable ")
        (rowHead.findViewById<View>(R.id.attrib_name4) as TextView).text=(" Fecha de cumplimiento ")
        table.addView(rowHead)

        for (i in 0 until temps.size) {
            val row = LayoutInflater.from(context).inflate(R.layout.attrib_row, null) as TableRow
            (row.findViewById<View>(R.id.attrib_name) as TextView).text=" "
            (row.findViewById<View>(R.id.attrib_value) as TextView).text=" "
            (row.findViewById<View>(R.id.attrib_name2) as TextView).text=(" Pruebas IT ")
            (row.findViewById<View>(R.id.attrib_value2) as TextView).text=(" Ivan Martinez ")
            (row.findViewById<View>(R.id.attrib_name3) as TextView).text=(" Ir de visita cada 8 dias ")
            (row.findViewById<View>(R.id.attrib_value3) as TextView).text=(" Oscar ")
            (row.findViewById<View>(R.id.attrib_name4) as TextView).text=(" 28-08-2020 ")
            table.addView(row)
        }
        table.requestLayout()
    }

}
