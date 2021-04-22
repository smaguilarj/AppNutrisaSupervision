package com.example.nutrisaapplication.ui.main.caja

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_caja.*
import kotlinx.android.synthetic.main.fragment_caja.buttonEnviarPlan
import kotlinx.android.synthetic.main.fragment_caja.imb_na11
import kotlinx.android.synthetic.main.fragment_caja.imb_na4
import kotlinx.android.synthetic.main.fragment_caja.imb_no11
import kotlinx.android.synthetic.main.fragment_caja.imb_no14
import kotlinx.android.synthetic.main.fragment_caja.imb_no20
import kotlinx.android.synthetic.main.fragment_caja.imb_no4
import kotlinx.android.synthetic.main.fragment_caja.imb_yes11
import kotlinx.android.synthetic.main.fragment_caja.imb_yes14
import kotlinx.android.synthetic.main.fragment_caja.imb_yes20
import kotlinx.android.synthetic.main.fragment_caja.imb_yes4
import kotlinx.android.synthetic.main.fragment_caja.img_na14
import kotlinx.android.synthetic.main.fragment_caja.img_na20
import kotlinx.android.synthetic.main.fragment_caja.img_question14
import kotlinx.android.synthetic.main.fragment_caja.img_question20
import kotlinx.android.synthetic.main.fragment_caja.img_question3
import kotlinx.android.synthetic.main.fragment_caja.img_question4
import kotlinx.android.synthetic.main.fragment_caja.progressBar
import kotlinx.android.synthetic.main.fragment_caja.tvProgress
import java.io.File

class CajaFragment : Fragment() {

    private val navigation by lazy {
        findNavController()
    }
    private var respuesta: String = ""
    private var pregunta = 0
    private var dbFireStore = FirebaseFirestore.getInstance()
    private var mapa = mutableMapOf<String, String>()
    private lateinit var mStorageReference: StorageReference
    private lateinit var mPhotoUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_caja, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mStorageReference = FirebaseStorage.getInstance().reference
        imb_yes11.setOnClickListener { tomaFoto(1) }
        imb_yes4.setOnClickListener { tomaFoto(2) }
        imb_no11.setOnClickListener { tomaFoto(3) }
        imb_no4.setOnClickListener { tomaFoto(4) }
        imb_yes20.setOnClickListener { tomaFoto(5) }
        imb_yes14.setOnClickListener { tomaFoto(6) }
        imb_no20.setOnClickListener { tomaFoto(7) }
        imb_no14.setOnClickListener { tomaFoto(8) }
        imb_yes15.setOnClickListener { tomaFoto(9) }
        imb_yes16.setOnClickListener { tomaFoto(10) }
        imb_yes17.setOnClickListener { tomaFoto(11) }
        imb_no15.setOnClickListener { tomaFoto(12) }
        imb_no16.setOnClickListener { tomaFoto(13) }
        imb_no17.setOnClickListener { tomaFoto(14) }
        imb_na11.setOnClickListener {
            pregunta = 11; respuesta = "NA";Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta11", respuesta)
        }
        imb_na4.setOnClickListener {
            pregunta = 12; respuesta = "NA";Log.d(
            "respuesta",
            "pregunta: $pregunta respuesta:$respuesta"
        )
            mapa.put("pregunta12", respuesta)
        }
        img_na20.setOnClickListener {
            pregunta = 13; respuesta = "NA";Log.d(
            "respuesta",
            "pregunta: $pregunta respuesta:$respuesta"
        )
            mapa.put("pregunta13", respuesta)
        }
        img_na14.setOnClickListener {
            pregunta = 14; respuesta = "NA";Log.d(
            "respuesta",
            "pregunta: $pregunta respuesta:$respuesta"
        )
            mapa.put("pregunta14", respuesta)
        }
        imb_na15.setOnClickListener {
            pregunta = 15; respuesta = "NA";Log.d(
            "respuesta",
            "pregunta: $pregunta respuesta:$respuesta"
        )
            //mapa.put("pregunta15",pregunta.toString())
            mapa.put("pregunta15", respuesta)
        }
        imb_na16.setOnClickListener {
            pregunta = 16; respuesta = "NA";Log.d(
            "respuesta",
            "pregunta: $pregunta respuesta:$respuesta"
        )
            //mapa.put("pregunta16",pregunta.toString())
            mapa.put("pregunta16", respuesta)
        }
        imb_na17.setOnClickListener {
            pregunta = 17; respuesta = "NA";Log.d(
            "respuesta",
            "pregunta: $pregunta respuesta:$respuesta"
        )
            //mapa.put("pregunta17",pregunta.toString())
            mapa.put("pregunta17", respuesta)
        }
        buttonEnviarPlan.setOnClickListener {
            navigation.navigate(R.id.action_cajaFragment_to_bodegaPizarraFragment); SharedApp.prefs.caja =
            true
            val name = SharedApp.prefs.pdfname
            if (name != null) {
                dbFireStore.collection("pdf").document(name).set(mapa, SetOptions.merge())
                    .addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(
                            requireContext(),
                            "se guardo correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("respuesta", "Documento guardado!")
                    })
                    .addOnFailureListener(OnFailureListener { e ->
                        Log.d("respuesta", "Error al escribir el documento", e)
                    })
            }
        }
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

    private fun savePhoto(fotoName: String) {
        val name = SharedApp.prefs.pdfname
        val storageRef = FirebaseStorage.getInstance().reference
        val ref = name?.let { storageRef.child("imagenes").child("$it/" + fotoName) }
        if (mPhotoUri != null) {
            ref?.putFile(mPhotoUri)
                ?.addOnProgressListener { it ->
                    val progress = (100 * it.bytesTransferred / it.totalByteCount).toDouble()
                    progressBar.let { pro ->
                        pro.progress = progress.toInt()
                        tvProgress.text = "Completado: $progress%"
                    }
                }
                ?.addOnSuccessListener {
                    Snackbar.make(requireView(), "Completado", Snackbar.LENGTH_SHORT).show()
                }
                ?.addOnFailureListener {
                    Snackbar.make(requireView(), "Error al subir la foto", Snackbar.LENGTH_SHORT)
                        .show()
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
            mPhotoUri = data?.data!!
            when (requestCode) {
                1 -> {
                    img_question3.setImageURI(fileUri);respuesta = "SI";pregunta = 11
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta11", respuesta)
                    savePhoto("fotoPregunta11")
                }
                2 -> {
                    img_question4.setImageURI(fileUri);respuesta = "SI";pregunta = 12
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta12", respuesta)
                    savePhoto("fotoPregunta12")
                }
                3 -> {
                    img_question3.setImageURI(fileUri);respuesta = "NO";pregunta = 11
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta11", respuesta)
                    savePhoto("fotoPregunta11")
                }
                4 -> {
                    img_question4.setImageURI(fileUri);respuesta = "NO";pregunta = 12
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta12", respuesta)
                    savePhoto("fotoPregunta12")
                }
                5 -> {
                    img_question20.setImageURI(fileUri);respuesta = "SI";pregunta = 13
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta13", respuesta)
                    savePhoto("fotoPregunta13")
                }
                6 -> {
                    img_question14.setImageURI(fileUri);respuesta = "SI";pregunta = 14
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta14", respuesta)
                    savePhoto("fotoPregunta14")
                }
                7 -> {
                    img_question20.setImageURI(fileUri);respuesta = "NO";pregunta = 13
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta13", respuesta)
                    savePhoto("fotoPregunta13")
                }
                8 -> {
                    img_question14.setImageURI(fileUri);respuesta = "NO";pregunta = 14
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta14", respuesta)
                    savePhoto("fotoPregunta14")
                }
                9 -> {
                    img_question15.setImageURI(fileUri);respuesta = "SI";pregunta = 15
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta15", respuesta)
                    savePhoto("fotoPregunta15")
                }
                10 -> {
                    img_question16.setImageURI(fileUri);respuesta = "SI";pregunta = 16
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta16", respuesta)
                    savePhoto("fotoPregunta16")
                }
                11 -> {
                    img_question17.setImageURI(fileUri);respuesta = "SI";pregunta = 17
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta17", respuesta)
                    savePhoto("fotoPregunta17")
                }
                12 -> {
                    img_question15.setImageURI(fileUri);respuesta = "NO";pregunta = 15
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta15", respuesta)
                    savePhoto("fotoPregunta15")
                }
                13 -> {
                    img_question16.setImageURI(fileUri);respuesta = "NO";pregunta = 16
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta16", respuesta)
                    savePhoto("fotoPregunta16")
                }
                14 -> {
                    img_question17.setImageURI(fileUri);respuesta = "NO";pregunta = 17
                    Log.d("respuesta", "pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta17", respuesta)
                    savePhoto("fotoPregunta17")
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
            Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}