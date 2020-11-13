package com.example.nutrisaapplication.ui.main.plan_trabajo

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.data.model.PlanTrabajoModel
import com.example.nutrisaapplication.ui.main.plan_trabajo.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_plan_dialog.*
import kotlinx.android.synthetic.main.fragment_plan_dialog.buttonEnviar
import java.util.*
import kotlin.collections.ArrayList


class PlanDialogFragment : Fragment() {

    private val navigation by lazy {
        findNavController()
    }
    private val planDialogViewModel by lazy {
        ViewModelProvider(requireActivity()).get(PlanViewModel::class.java)
    }

    private var selectedDate:String=""
    var status: String=""
    val list: MutableList<PlanTrabajoModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtFechaCumplimiento.setOnClickListener { showDatePickerDialog() }

        buttonEnviar.setOnClickListener {

            if (radioButtonRojo.isChecked){
                status = "Rojo"
                Log.i("list","resulado: $status")
            }
            if (radioButtonAmarillo.isChecked){
                status= "Amarillo"
                Log.i("list","resulado: $status")
            }
            if (radioButtonVerde.isChecked){
                status= "Verde"
                Log.i("list","resulado:  $status")
            }
            checkInf()?.let { it1 -> planDialogViewModel.setListData(it1) }
        }
    }
   fun checkInf():MutableList<PlanTrabajoModel>?  {
        if (selectedDate.isNotEmpty() && edtTienda.text.toString().isNotEmpty() && edtGerente.text.toString().isNotEmpty() && edtAcciones.text.toString().isNotEmpty()&&edtResponsable.text.toString().isNotEmpty()){
            val planTrabajo = PlanTrabajoModel(edtTienda.text.toString(), edtGerente.text.toString(), edtAcciones.text.toString(),edtResponsable.text.toString(),selectedDate,status)
            //Toast.makeText(requireContext(), "este es el modelo: $planTrabajo", Toast.LENGTH_SHORT).show()
            //val list = listOf<PlanTrabajoModel>(planTrabajo)
            list.add(planTrabajo)
            Log.i("list", "resultado de datos: $planTrabajo")
            navigation.navigate(R.id.action_planDialogFragment_to_planTrabajoFragment)
            return list
        }/*else{
            Toast.makeText(requireContext(), "todos los datos son obligatorios", Toast.LENGTH_SHORT).show()
            return null
        }*/
       return null
    }

    /* fun crearListaPlan():List<PlanTrabajoModel> {
         val plan = PlanTrabajoModel(edtTienda.text.toString(), edtGerente.text.toString(), edtAcciones.text.toString(),edtResponsable.text.toString(),selectedDate,status)
         //val plan = PlanTrabajoModel("prueba it","ivan","ninguna","Antonio","2/10/20","Verde")
         val list= listOf<PlanTrabajoModel>(plan)
        return list
    }*/

    private fun showDatePickerDialog() {
       /* val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")*/
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            selectedDate = day.toString() + "/" + (month + 1) + "/" + year
            //fecha en string
            edtFechaCumplimiento.setText(selectedDate)
        })
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    class DatePickerFragment : DialogFragment() {

        private var listener: DatePickerDialog.OnDateSetListener? = null

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            return DatePickerDialog(requireActivity(), listener, year, month, day)
        }

        companion object {
            fun newInstance(listener: DatePickerDialog.OnDateSetListener): DatePickerFragment {
                val fragment = DatePickerFragment()
                fragment.listener = listener
                return fragment
            }
        }

    }
}