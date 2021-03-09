package com.example.nutrisaapplication.ui.main.prevencionPerdidas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nutrisaapplication.R
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_prevencion_perdidas.*
import java.io.File


class PrevencionPerdidasFragment : Fragment() {

    private var respuesta:String=""
    private var pregunta=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prevencion_perdidas, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkCamara()
        buttonEnviar_perdida.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun checkCamara() {
        imb_yes1.setOnClickListener { tomaFoto(1) }
        imb_yes4.setOnClickListener { tomaFoto(2) }
        imb_yes3.setOnClickListener { tomaFoto(3) }
        imb_yes6.setOnClickListener { tomaFoto(4) }
        imb_yes7.setOnClickListener { tomaFoto(5) }
        imb_yes8.setOnClickListener { tomaFoto(6) }
        imb_no1.setOnClickListener { tomaFoto(7) }
        imb_no2.setOnClickListener { tomaFoto(8) }
        imb_no3.setOnClickListener { tomaFoto(9) }
        imb_no6.setOnClickListener { tomaFoto(10) }
        imb_no7.setOnClickListener { tomaFoto(11) }
        imb_no8.setOnClickListener { tomaFoto(12) }
        imb_na1.setOnClickListener { pregunta=1; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na2.setOnClickListener { pregunta=2; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na3.setOnClickListener { pregunta=3; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na6.setOnClickListener { pregunta=4; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na7.setOnClickListener { pregunta=5; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na8.setOnClickListener { pregunta=6; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        // imb_na39.setOnClickListener { pregunta=39; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
    }

    private fun tomaFoto(code: Int) {
        ImagePicker.with(this)
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
                    img_question1.setImageURI(fileUri);respuesta="SI";pregunta=1; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                2 -> {
                    img_question2.setImageURI(fileUri);respuesta="SI";pregunta=2; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                3 -> {
                    img_question3.setImageURI(fileUri);respuesta="SI";pregunta=3; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                4-> {
                    img_question6.setImageURI(fileUri);respuesta="SI";pregunta=4; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                5 -> {
                    img_question7.setImageURI(fileUri);respuesta="SI";pregunta=5; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                6 -> {
                    img_question8.setImageURI(fileUri);respuesta="SI";pregunta=6; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                7-> {
                    img_question1.setImageURI(fileUri);respuesta="NO";pregunta=1; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                8 -> {
                    img_question2.setImageURI(fileUri);respuesta="NO";pregunta=2; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                9 -> {
                    img_question3.setImageURI(fileUri);respuesta="NO";pregunta=3; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                10 -> {
                    img_question6.setImageURI(fileUri);respuesta="NO";pregunta=4; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                11-> {
                    img_question7.setImageURI(fileUri);respuesta="NO";pregunta=5; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                12 -> {
                    img_question8.setImageURI(fileUri);respuesta="NO";pregunta=6; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
            }
            //You can get File object from intent
            val file: File? = ImagePicker.getFile(data)
            Log.i("Tag", "resultado = $file")
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)
            Log.i("Tag", "resultado = $filePath")
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } /*else {
            //Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }*/
    }
}