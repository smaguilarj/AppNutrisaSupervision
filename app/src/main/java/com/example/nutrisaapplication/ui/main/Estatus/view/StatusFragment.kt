package com.example.nutrisaapplication.ui.main.Estatus.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import kotlinx.android.synthetic.main.activity_basico_exterior.*
import kotlinx.android.synthetic.main.fragment_status.*

class StatusFragment : Fragment() {


    private val navigation by lazy {
        findNavController()
    }

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
        image()
        siguiente.setOnClickListener {
            navigation.navigate(R.id.action_statusFragment_to_salidaVisitaRapidaFragment)
        }
        textViewTienda.text = SharedApp.prefs.pdfname
    }

    override fun onResume() {
        super.onResume()
        image()
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
    private fun image(){
        val fachada = SharedApp.prefs.fachada
        val piso = SharedApp.prefs.piso
        val barra = SharedApp.prefs.barras
        val caja = SharedApp.prefs.caja
        val bodega = SharedApp.prefs.bodega
        val plan = SharedApp.prefs.plan

        if(fachada){
            imageViewFachada.setImageResource(R.drawable.ic_heck_circle_25)
        }else{
            imageViewFachada.setImageResource(R.drawable.ic_cancel_no)
        }
        if(piso){
            imageViewPiso.setImageResource(R.drawable.ic_heck_circle_25)
        }else{
            imageViewPiso.setImageResource(R.drawable.ic_cancel_no)
        }
        if(barra){
            imageViewBarra.setImageResource(R.drawable.ic_heck_circle_25)
        }else{
            imageViewBarra.setImageResource(R.drawable.ic_cancel_no)
        }
        if(caja){
            imageViewCaja.setImageResource(R.drawable.ic_heck_circle_25)
        }else{
            imageViewCaja.setImageResource(R.drawable.ic_cancel_no)
        }
        if(bodega){
            imageViewBodega.setImageResource(R.drawable.ic_heck_circle_25)
        }else{
            imageViewBodega.setImageResource(R.drawable.ic_cancel_no)
        }
        if(plan){
            imageViewPlanTrabajo.setImageResource(R.drawable.ic_heck_circle_25)
        }else{
            imageViewPlanTrabajo.setImageResource(R.drawable.ic_cancel_no)
        }
    }
}