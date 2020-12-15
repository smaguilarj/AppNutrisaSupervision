package com.example.nutrisaapplication.ui.main.planTrabajoAuditoria

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.data.model.PlanTrabajoModel
import com.example.nutrisaapplication.ui.main.plan_trabajo.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_plan_trabajo.*
import java.util.ArrayList


class PlanTrabajoAuditoriaFragment : Fragment() {

    private lateinit var table: TableLayout
    var list: MutableList<PlanTrabajoModel> = mutableListOf()

    private val navigation by lazy {
        findNavController()
    }
    private val planTrabajoViewModel by lazy {
        ViewModelProvider(requireActivity()).get(PlanViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_trabajo_auditoria, container, false)
    }

    lateinit var entries: ArrayList<PlanTrabajoModel>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        entries = ArrayList<PlanTrabajoModel>()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        table = view.findViewById<View>(R.id.simpleTableLayout) as TableLayout
        initObserver()
        buttonEnviarPlan.setOnClickListener {
            SharedApp.prefs.plan=true
            SharedApp.prefs.borrarLista=true
            entries.clear()
            navigation.navigate(R.id.action_planTrabajoFragment_to_statusFragment)
        }
        buttonAgregarPlan.setOnClickListener {
            navigation.navigate(R.id.action_planTrabajoFragment_to_planDialogFragment)
        }

        val plan =planTrabajoViewModel.getListData()
        Log.i("list", "lista de planes: $plan")
    }

    private fun CreateTable(lista: MutableList<PlanTrabajoModel>)
    {
        val algo = lista.size
        Log.i("list","tamaño lista: $algo")
        if(lista.size>1){
            val rowHead = LayoutInflater.from(context).inflate(R.layout.header_row, null) as TableRow
            rowHead.setBackgroundResource(R.color.colorBlack)
            rowHead.setLayoutParams(TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT))
            (rowHead.findViewById<View>(R.id.attrib_name2) as TextView).text=(" Tienda ")
            (rowHead.findViewById<View>(R.id.attrib_name2) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_value2) as TextView).text=(" Gerente de turno ")
            (rowHead.findViewById<View>(R.id.attrib_value2) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_name3) as TextView).text=(" Acciones a tomar ")
            (rowHead.findViewById<View>(R.id.attrib_name3) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_value3) as TextView).text=(" Responsable ")
            (rowHead.findViewById<View>(R.id.attrib_value3) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_name4) as TextView).text=(" Fecha de cumplimiento ")
            (rowHead.findViewById<View>(R.id.attrib_name4) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attributo_status) as TextView).text=(" Status ")
            (rowHead.findViewById<View>(R.id.attributo_status) as TextView).setTextColor(
                ContextCompat.getColor(requireContext(), R.color.white)
            )
            table.addView(rowHead)

            lista.let {
                for (i in lista) {
                    val row = LayoutInflater.from(context).inflate(R.layout.attrib_row, null) as TableRow
                    row.setLayoutParams(TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT))
                    (row.findViewById<View>(R.id.attrib_name2) as TextView).text=(i.tienda)
                    (row.findViewById<View>(R.id.attrib_value2) as TextView).text=(i.nombreGerente)
                    (row.findViewById<View>(R.id.attrib_name3) as TextView).text=(i.acciones)
                    (row.findViewById<View>(R.id.attrib_value3) as TextView).text=(i.responsable)
                    (row.findViewById<View>(R.id.attrib_name4) as TextView).text=(i.fecha)
                    if(i.status=="Verde"){
                        (row.findViewById<View>(R.id.imb_status) as ImageView).setImageResource(R.drawable.ic_circulo_verde)
                    }else if (i.status=="Rojo"){
                        (row.findViewById<View>(R.id.imb_status) as ImageView).setImageResource(R.drawable.ic_circulo_rojo)
                    }else{
                        (row.findViewById<View>(R.id.imb_status) as ImageView).setImageResource(R.drawable.ic_circulo_amarillo)
                    }
                    table.addView(row)
                }
            }
        }else{
            val rowHead = LayoutInflater.from(context).inflate(R.layout.header_row, null) as TableRow
            rowHead.setBackgroundResource(R.color.colorBlack)
            rowHead.setLayoutParams(TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT))
            (rowHead.findViewById<View>(R.id.attrib_name2) as TextView).text=(" Tienda ")
            (rowHead.findViewById<View>(R.id.attrib_name2) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_value2) as TextView).text=(" Gerente de turno ")
            (rowHead.findViewById<View>(R.id.attrib_value2) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_name3) as TextView).text=(" Acciones a tomar ")
            (rowHead.findViewById<View>(R.id.attrib_name3) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_value3) as TextView).text=(" Responsable ")
            (rowHead.findViewById<View>(R.id.attrib_value3) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attrib_name4) as TextView).text=(" Fecha de cumplimiento ")
            (rowHead.findViewById<View>(R.id.attrib_name4) as TextView).setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            (rowHead.findViewById<View>(R.id.attributo_status) as TextView).text=(" Status ")
            (rowHead.findViewById<View>(R.id.attributo_status) as TextView).setTextColor(
                ContextCompat.getColor(requireContext(), R.color.white)
            )
            table.addView(rowHead)

            lista.let {
                for (i in lista) {
                    val row = LayoutInflater.from(context).inflate(R.layout.attrib_row, null) as TableRow
                    row.setLayoutParams(TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT))
                    (row.findViewById<View>(R.id.attrib_name2) as TextView).text=(i.tienda)
                    (row.findViewById<View>(R.id.attrib_value2) as TextView).text=(i.nombreGerente)
                    (row.findViewById<View>(R.id.attrib_name3) as TextView).text=(i.acciones)
                    (row.findViewById<View>(R.id.attrib_value3) as TextView).text=(i.responsable)
                    (row.findViewById<View>(R.id.attrib_name4) as TextView).text=(i.fecha)
                    if(i.status=="Verde"){
                        (row.findViewById<View>(R.id.imb_status) as ImageView).setImageResource(R.drawable.ic_circulo_verde)
                    }else if (i.status=="Rojo"){
                        (row.findViewById<View>(R.id.imb_status) as ImageView).setImageResource(R.drawable.ic_circulo_rojo)
                    }else{
                        (row.findViewById<View>(R.id.imb_status) as ImageView).setImageResource(R.drawable.ic_circulo_amarillo)
                    }
                    table.addView(row)
                }
            }
        }
        table.requestLayout()
    }

    val planTrabajObserver= Observer<MutableList<PlanTrabajoModel>>{
        Log.d("list", it.toString())
        for (i in it){
            entries.add(i)
        }
        //list.addAll(it)
        CreateTable(it)
        //Log.d("list", "$list")
        Log.d("list", "$entries")
    }

    fun initObserver() {
        planTrabajoViewModel.listData.observe(viewLifecycleOwner, planTrabajObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        entries.clear()
    }

}