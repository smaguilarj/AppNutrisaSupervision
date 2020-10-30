package com.example.nutrisaapplication.ui.main.fachada

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_fachada.*
import java.io.File

class FachadaFragment : Fragment() {

    private var respuesta:String=""
    private var pregunta=0
    private var respuesta2:String=""
    private var pregunta2=0
    private var completo1=false
    private var completo2=false

    private val navigation by lazy {
        findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fachada, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imb_yes1.setOnClickListener { tomaFoto(1);Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_yes4.setOnClickListener { tomaFoto(2);Log.d("respuesta","pregunta: $pregunta2 respuesta:$respuesta2") }
        imb_no1.setOnClickListener { tomaFoto(3);Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_no4.setOnClickListener { tomaFoto(4);Log.d("respuesta","pregunta: $pregunta2 respuesta:$respuesta2") }
        imb_na3.setOnClickListener { pregunta=1; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta");completo1=true; habilitarBtn() }
        imb_na4.setOnClickListener { pregunta=2; respuesta="NA";Log.d("respuesta","pregunta: $pregunta2 respuesta:$respuesta2");completo2=true; habilitarBtn() }
        buttonFachada.isEnabled=false
        buttonFachada.setOnClickListener {
            SharedApp.prefs.fachada= true
            navigation.navigate(R.id.action_fachadaFragment_to_pisoFragment)
        }
    }

    private fun habilitarBtn() {
       if(completo1 && completo2) {
           buttonFachada.isEnabled=true
       }else{
           buttonFachada.isEnabled=false
       }
    }
    // startActivity(Intent(this,PisoActivity::class.java))

    private fun tomaFoto(code: Int) {
        ImagePicker.with(this)
            .cameraOnly()
            .crop(1f, 1f)//Crop Square image(Optional)
            .compress(1024) //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                480,
                480
            )  //Final image resolution will be less than 1080 x 1080(Optional)
            .start(code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
            when (requestCode) {
                1 -> {
                    img_question3.setImageURI(fileUri);respuesta="SI";pregunta=1
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    completo1=true
                }
                2 -> {
                    img_question4.setImageURI(fileUri);respuesta="SI";pregunta=2
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    completo2=true
                }
                3 -> {
                    img_question3.setImageURI(fileUri);respuesta="NO";pregunta=1
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    completo1=true
                }
                4-> {
                    img_question4.setImageURI(fileUri);respuesta="NO";pregunta=2
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    completo2=true
                }
            }
            //You can get File object from intent
            val file: File? = ImagePicker.getFile(data)
            Log.i("Tag", "resultado = $file")
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)
            Log.i("Tag", "resultado = $filePath")
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();completo1=false;completo2=false
            respuesta=""
            respuesta2=""
            pregunta=0
            pregunta2=0
        } else {
            completo1=false;completo2=false
        }
        habilitarBtn()
    }
}