package com.example.nutrisaapplication.ui.main.barra

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
import com.example.nutrisaapplication.data.SharedApp
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.fragment_barra.*
import java.io.File


class BarraFragment : Fragment() {
    var respuesta: String = ""
    var pregunta = 0
    private var dbFireStore = FirebaseFirestore.getInstance()
    private var mapa = mutableMapOf<String, String>()

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
        imb_yes4.setOnClickListener { tomaFoto(2) }
        imb_no11.setOnClickListener { tomaFoto(3) }
        imb_no4.setOnClickListener { tomaFoto(4) }
        imb_yes20.setOnClickListener { tomaFoto(5) }
        imb_yes14.setOnClickListener { tomaFoto(6) }
        imb_no20.setOnClickListener { tomaFoto(7) }
        imb_no14.setOnClickListener { tomaFoto(8) }
        imb_na11.setOnClickListener {
            pregunta = 7; respuesta = "NA";Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta7",pregunta.toString())
            mapa.put("respuesta7",respuesta)
        }
        imb_na4.setOnClickListener {
            pregunta = 8; respuesta = "NA";Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta8",pregunta.toString())
            mapa.put("respuesta8",respuesta)
        }
        img_na20.setOnClickListener {
            pregunta= 9; respuesta = "NA";Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta9",pregunta.toString())
            mapa.put("respuesta9",respuesta)
        }
        img_na14.setOnClickListener {
            pregunta = 10; respuesta = "NA";Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta10",pregunta.toString())
            mapa.put("respuesta10",respuesta)
        }

       // habilitarBoton()
        buttonEnviarPlan.setOnClickListener {
            SharedApp.prefs.barras = true
            navigate.navigate(R.id.action_barraFragment_to_cajaFragment)
            val name = SharedApp.prefs.pdfname
            if (name != null) {
                dbFireStore.collection("pdf").document(name).set(mapa, SetOptions.merge())
                    .addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(
                            requireContext(),
                            "se guardo correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("respuesta", "Documento escrito correctamente!")
                    })
                    .addOnFailureListener(OnFailureListener { e ->
                        Log.d("respuesta", "Error al escribir el documento", e)
                    })
            }
        }
    }

    private fun habilitarBoton() {

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
                    img_question3.setImageURI(fileUri);respuesta = "SI";pregunta = 7
                    Log.d("respuesta", "pregunta7: $pregunta respuesta7:$respuesta")
                    mapa.put("pregunta7",pregunta.toString())
                    mapa.put("respuesta7",respuesta)
                }
                2 -> {
                    img_question4.setImageURI(fileUri);respuesta = "SI";pregunta = 8
                    Log.d("respuesta", "pregunta8: $pregunta respuesta8:$respuesta")
                    mapa.put("pregunta8",pregunta.toString())
                    mapa.put("respuesta8",respuesta)
                }
                3 -> {
                    img_question3.setImageURI(fileUri);respuesta = "NO";pregunta = 7
                    Log.d("respuesta", "pregunta7: $pregunta respuesta7:$respuesta")
                    mapa.put("pregunta7",pregunta.toString())
                    mapa.put("respuesta7",respuesta)
                }
                4 -> {
                    img_question4.setImageURI(fileUri);respuesta = "NO";pregunta = 8
                    Log.d("respuesta", "pregunta8: $pregunta respuesta8:$respuesta")
                    mapa.put("pregunta8",pregunta.toString())
                    mapa.put("respuesta8",respuesta)
                }
                5 -> {
                    img_question20.setImageURI(fileUri);respuesta = "SI";pregunta = 9
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta9",pregunta.toString())
                    mapa.put("respuesta9",respuesta)
                }
                6 -> {
                    img_question14.setImageURI(fileUri);respuesta = "SI";pregunta = 10
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta10",pregunta.toString())
                    mapa.put("respuesta10",respuesta)
                }
                7 -> {
                    img_question20.setImageURI(fileUri);respuesta = "NO";pregunta = 9
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta9",pregunta.toString())
                    mapa.put("respuesta9",respuesta)
                }
                8 -> {
                    img_question14.setImageURI(fileUri);respuesta = "NO";pregunta = 10
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta10",pregunta.toString())
                    mapa.put("respuesta10",respuesta)
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