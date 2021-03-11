package com.example.nutrisaapplication.ui.main.piso.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_piso.*
import kotlinx.android.synthetic.main.fragment_piso.buttonEnviarPlan
import kotlinx.android.synthetic.main.fragment_piso.imb_na4
import kotlinx.android.synthetic.main.fragment_piso.imb_no14
import kotlinx.android.synthetic.main.fragment_piso.imb_no20
import kotlinx.android.synthetic.main.fragment_piso.imb_no4
import kotlinx.android.synthetic.main.fragment_piso.imb_yes14
import kotlinx.android.synthetic.main.fragment_piso.imb_yes20
import kotlinx.android.synthetic.main.fragment_piso.imb_yes4
import kotlinx.android.synthetic.main.fragment_piso.img_na14
import kotlinx.android.synthetic.main.fragment_piso.img_na20
import kotlinx.android.synthetic.main.fragment_piso.img_question14
import kotlinx.android.synthetic.main.fragment_piso.img_question20
import kotlinx.android.synthetic.main.fragment_piso.img_question3
import kotlinx.android.synthetic.main.fragment_piso.img_question4
import java.io.File

class PisoFragment : Fragment() {

    var respuesta:String=""
    var pregunta=0
    private var dbFireStore = FirebaseFirestore.getInstance()
    private var mapa= mutableMapOf<String,String>()
    private lateinit var mPhotoUri: Uri

    private val navigation by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_piso, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imb_yes1.setOnClickListener { tomaFoto(1) }
        imb_yes4.setOnClickListener { tomaFoto(2) }
        imb_no1.setOnClickListener { tomaFoto(3) }
        imb_no4.setOnClickListener { tomaFoto(4) }
        imb_yes20.setOnClickListener { tomaFoto(5) }
        imb_yes14.setOnClickListener { tomaFoto(6) }
        imb_no20.setOnClickListener { tomaFoto(7) }
        imb_no14.setOnClickListener { tomaFoto(8) }
        imb_na3.setOnClickListener { pregunta=3; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta3",respuesta)}
        imb_na4.setOnClickListener { pregunta=4; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta4",respuesta)}
        img_na20.setOnClickListener { pregunta=5; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta5",respuesta)}
        img_na14.setOnClickListener { pregunta=6; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
            mapa.put("pregunta6",respuesta)}
        buttonEnviarPlan.setOnClickListener {
            navigation.navigate(R.id.action_pisoFragment_to_barraFragment);SharedApp.prefs.piso=true
            val name=SharedApp.prefs.pdfname
            if (name != null) {
                dbFireStore.collection("pdf").document(name).set(mapa, SetOptions.merge())
                    .addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(requireContext(), "se guardo correctamente", Toast.LENGTH_LONG).show()
                        Log.d("respuesta", "Documento escrito correctamente!")
                    })
                    .addOnFailureListener(OnFailureListener {
                            e -> Log.d("respuesta", "Error al escribir el documento", e)
                    })
            }
        }
    }

    private fun savePhoto(fotoName: String){
        val name = SharedApp.prefs.pdfname
        val storageRef = FirebaseStorage.getInstance().reference
        val ref = name?.let { storageRef.child("imagenes").child("$it/"+fotoName) }
        if(mPhotoUri != null){
            ref?.putFile(mPhotoUri)
                ?.addOnProgressListener {
                    val progress= (100*it.bytesTransferred/it.totalByteCount).toDouble()
                    progressBarP.progress= progress.toInt()
                    tvProgressP.text= "Completado: $progress%"
                }
                ?.addOnCompleteListener{
                    //progressBar.visibility= View.INVISIBLE
                    //Toast.makeText(requireContext(), "el porcentaje es 100%", Toast.LENGTH_SHORT).show()
                }
                ?.addOnSuccessListener {
                    Snackbar.make(requireView(), "success", Snackbar.LENGTH_SHORT).show()
                }
                ?.addOnFailureListener {
                    Snackbar.make(requireView(), "error al subir:", Snackbar.LENGTH_SHORT).show()
                }
        }
    }
    //onRequestChangeFragment(BarraFragment(), R.id.nav_host_fragment, false, "")
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
            mPhotoUri = data?.data!!
            when (requestCode) {
                1 -> {
                    img_question3.setImageURI(mPhotoUri);respuesta="SI";pregunta=3
                    mapa.put("pregunta3",respuesta)
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    savePhoto("fotoPregunta3")
                }
                2 -> {
                    img_question4.setImageURI(mPhotoUri);respuesta="SI";pregunta=4
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta4",respuesta)
                    savePhoto("fotoPregunta4")
                }
                3 -> {
                    img_question3.setImageURI(mPhotoUri);respuesta="NO";pregunta=3
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta3",respuesta)
                    savePhoto("fotoPregunta3")
                }
                4-> {
                    img_question4.setImageURI(mPhotoUri);respuesta="NO";pregunta=4
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta4",respuesta)
                    savePhoto("fotoPregunta4")
                }
                5 -> {
                    img_question20.setImageURI(mPhotoUri);respuesta="SI";pregunta=5
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta5",respuesta)
                    savePhoto("fotoPregunta5")
                }
                6-> {
                    img_question14.setImageURI(mPhotoUri);respuesta="SI";pregunta=6
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta6",respuesta)
                    savePhoto("fotoPregunta6")
                }
                7 -> {
                    img_question20.setImageURI(mPhotoUri);respuesta="NO";pregunta=5
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta5",respuesta)
                    savePhoto("fotoPregunta5")
                }
                8-> {
                    img_question14.setImageURI(mPhotoUri);respuesta="NO";pregunta=6
                    Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                    mapa.put("pregunta6",respuesta)
                    savePhoto("fotoPregunta6")
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