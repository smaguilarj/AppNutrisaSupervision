package com.example.nutrisaapplication.ui.main.barra

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
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_barra.*
import java.io.File


class BarraFragment : Fragment() {
    var respuesta:String=""
    var pregunta=0

    private val navigate by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imb_yes11.setOnClickListener { tomaFoto(1) }
        imb_yes19.setOnClickListener { tomaFoto(2) }
        imb_no11.setOnClickListener { tomaFoto(3) }
        imb_no19.setOnClickListener { tomaFoto(4) }
        imb_yes20.setOnClickListener { tomaFoto(5) }
        imb_yes14.setOnClickListener { tomaFoto(6) }
        imb_no20.setOnClickListener { tomaFoto(7) }
        imb_no14.setOnClickListener { tomaFoto(8) }
        imb_na11.setOnClickListener { pregunta=7; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na19.setOnClickListener { pregunta=8; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        img_na20.setOnClickListener { pregunta=9; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        img_na14.setOnClickListener { pregunta=10; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}

        buttonEnviar.setOnClickListener { navigate.navigate(R.id.action_barraFragment_to_cajaFragment) }
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
                    img_question18.setImageURI(fileUri);respuesta="SI";pregunta=7
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                2 -> {
                    img_question19.setImageURI(fileUri);respuesta="SI";pregunta=8
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                3 -> {
                    img_question18.setImageURI(fileUri);respuesta="NO";pregunta=7
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                4-> {
                    img_question19.setImageURI(fileUri);respuesta="NO";pregunta=8
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                5 -> {
                    img_question20.setImageURI(fileUri);respuesta="SI";pregunta=9
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                6-> {
                    img_question14.setImageURI(fileUri);respuesta="SI";pregunta=10
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                7 -> {
                    img_question20.setImageURI(fileUri);respuesta="NO";pregunta=9
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                8-> {
                    img_question14.setImageURI(fileUri);respuesta="NO";pregunta=10
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
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}