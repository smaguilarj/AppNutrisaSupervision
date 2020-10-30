package com.example.nutrisaapplication.ui.main.inicioVisitaLarga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.activity_list_questions.*

class VisitaLargaFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita_larga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun obtenerDatos() {
        val region = arrayOf("Selecciona tu region","CENTRO NORTE", "CENTRO SUR", "NORTE GOLFO", "PACIFICO", "FRANQUICIAS")
        val array_adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, region)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerL.adapter = array_adapter
        spinnerL.onItemSelectedListener = this

        val distrito = arrayOf("Selecciona tu distrito","CENTRO", "COACALCO", "GAM", "NAUCALPAN", "NEZAHUALCOYOTL","TLANEPANTLA", "TOLUCA", "BENITO JUAREZ", "COAPA", "COYOACAN","MORELOS", "POLANCO", "SANTA FE", "TLAHUAC", "TLALPAN","HUASTECA POTOSINA", "MONTERREY 1", "MONTERREY 2", "MONTERREY 3", "NOROESTE","QUINTANA ROO", "VERACRUZ 1", "VERACRUZ 2", "VILLA HERMOSA", "GUADALAJARA 1", "GUADALAJARA 2", "GUADALAJARA 3", "GUANAJUATO", "GUERRERO","LEON", "OAXACA CHIAPAS", "QUERETARO", "FR CN", "FR CS", "FR NG", "FR PC")
        val array_adapter_distrito = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, distrito)
        array_adapter_distrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_distritoL.adapter = array_adapter_distrito
        spinner_distritoL.onItemSelectedListener = this

        val tienda = arrayOf("Selecciona tu tienda","1 UNIVERSIDAD", "2 PERISUR", "3 PLAZA DE LAS ESTRELLAS 1", "4 GALERIAS TABASCO", "99999 PRUEBAS IT")
        val array_adapter_tienda = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, tienda)
        array_adapter_tienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_tiendaL.adapter = array_adapter_tienda
        spinner_tiendaL.onItemSelectedListener = this

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //Toast.makeText(requireContext(), "Se selecciono: ${p0?.get(p2)}", Toast.LENGTH_SHORT).show()
    }
}