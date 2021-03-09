package com.example.nutrisaapplication.ui.main.basicoExterior

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_basico_exterior.*
import java.io.File


class BasicoExteriorFragment : Fragment() {

    private var respuesta:String=""
    private var pregunta=0

    private val navigation by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basico_exterior, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOption()
        imb_yes1.setOnClickListener { tomaFoto(1) }
        imb_yes4.setOnClickListener { tomaFoto(2) }
        imb_no3.setOnClickListener { tomaFoto(3) }
        imb_no2.setOnClickListener { tomaFoto(4) }
        imb_na1.setOnClickListener { pregunta=1; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na2.setOnClickListener { pregunta=2; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        buttonEnviarPlan.setOnClickListener {navigation.navigate(R.id.action_cajaFragment_to_bodegaPizarraFragment)}
    }


    private fun checkOption() {
        var status = "VISIBLE"
        var status2 = "VISIBLE"
        imageButton1.setOnClickListener {
            if (status == "VISIBLE") {
                tv_imgbutton1.visibility = View.VISIBLE
                status = ""
            } else {
                tv_imgbutton1.visibility = View.INVISIBLE
                status = "VISIBLE"
            }
        }
        imageButton2.setOnClickListener {
            if (status2 == "VISIBLE") {
                tv_imgbutton2.visibility = View.VISIBLE
                status2 = ""
            } else {
                tv_imgbutton2.visibility = View.INVISIBLE
                status2 = "VISIBLE"
            }
        }
    }

    private fun tomaFoto(code: Int) {
        ImagePicker.with(requireActivity())
            .cameraOnly()
            .crop(1f, 1f)//Crop Square image(Optional)
            .compress(1024) //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                400,
                400
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
                    img_question1.setImageURI(fileUri);respuesta="SI";pregunta=1
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                2 -> {
                    img_question2.setImageURI(fileUri);respuesta="SI";pregunta=2
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                3 -> {
                    img_question1.setImageURI(fileUri);respuesta="NO";pregunta=1
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                4-> {
                    img_question2.setImageURI(fileUri);respuesta="NO";pregunta=2
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
            }
            //You can get File object from intent
            val file: File? = ImagePicker.getFile(data)
            Log.i("Tag", "resultado = $file")
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)
            Log.i("Tag", "resultado = $filePath")
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
           //Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}