package com.example.nutrisaapplication.ui.main.fachada

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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_fachada.*
import kotlinx.android.synthetic.main.fragment_fachada.imb_na4
import kotlinx.android.synthetic.main.fragment_fachada.imb_no4
import kotlinx.android.synthetic.main.fragment_fachada.imb_yes4
import kotlinx.android.synthetic.main.fragment_fachada.img_question3
import kotlinx.android.synthetic.main.fragment_fachada.img_question4
import java.io.File

class FachadaFragment : Fragment() {

    private var respuesta:String=""
    private var pregunta=0
    private var respuesta2:String=""
    private var pregunta2=0
    private var completo1=false
    private var completo2=false
    private var dbFireStore = FirebaseFirestore.getInstance()
    var mapa = mutableMapOf<String,String>()
    private lateinit var mPhotoUri: Uri

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
        imb_yes1.setOnClickListener { tomaFoto(1);Log.d("respuesta","pregunta1: $pregunta respuesta1:$respuesta");habilitarBtn()}
        imb_yes4.setOnClickListener { tomaFoto(2);Log.d("respuesta","pregunta2: $pregunta2 respuesta2:$respuesta2");habilitarBtn() }
        imb_no1.setOnClickListener { tomaFoto(3);Log.d("respuesta","pregunta1: $pregunta respuesta1:$respuesta");habilitarBtn() }
        imb_no4.setOnClickListener { tomaFoto(4);Log.d("respuesta","pregunta2: $pregunta2 respuesta2:$respuesta2");habilitarBtn() }
        imb_na1.setOnClickListener { pregunta=1; respuesta="NA";Log.d("respuesta","pregunta1: $pregunta respuesta1:$respuesta");completo1=true; habilitarBtn();mapa.put("pregunta1",respuesta) }
        imb_na4.setOnClickListener { pregunta2=2; respuesta2="NA";Log.d("respuesta","pregunta2: $pregunta2 respuesta2:$respuesta2");completo2=true; habilitarBtn(); mapa.put("pregunta2",respuesta2) }
        buttonFachada.isEnabled=false
        buttonFachada.setOnClickListener {
            SharedApp.prefs.fachada= true
            navigation.navigate(R.id.action_fachadaFragment_to_pisoFragment)
            val name=SharedApp.prefs.pdfname
            if (name != null) {
                dbFireStore.collection("pdf").document(name).set(mapa)
                    .addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(requireContext(), "incercion correcta", Toast.LENGTH_LONG).show()
                        Log.d("respuesta", "Documento escrito correctamente!")
                    })
                    .addOnFailureListener(OnFailureListener {
                            e -> Log.d("respuesta", "Error al escribir el documento", e)
                    })
            }
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

    private fun savePhoto(fotoName: String){
        val name = SharedApp.prefs.pdfname
        val storageRef = FirebaseStorage.getInstance().reference
        val ref = name?.let { storageRef.child("imagenes").child("$it/"+fotoName) }
        if(mPhotoUri != null){
            ref?.putFile(mPhotoUri)
                ?.addOnProgressListener {
                    val progress= (100*it.bytesTransferred/it.totalByteCount).toDouble()
                    progressBar2.progress= progress.toInt()
                    tvProgressF.text= "Completado: $progress%"
                }
                ?.addOnCompleteListener{
                    //progressBar.visibility= View.INVISIBLE
                    //Toast.makeText(requireContext(), "el porcentaje es 100%", Toast.LENGTH_SHORT).show()
                }
                ?.addOnSuccessListener {
                    Snackbar.make(requireView(), "Completado", Snackbar.LENGTH_SHORT).show()
                }
                ?.addOnFailureListener {
                    Snackbar.make(requireView(), "Error al subir la foto", Snackbar.LENGTH_SHORT).show()
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
             mPhotoUri = data?.data!!
            when (requestCode) {
                1 -> {
                    img_question3.setImageURI(mPhotoUri);respuesta="SI";pregunta=1
                    Log.d("respuesta","pregunta1: $pregunta respuesta1:$respuesta")
                    completo1=true
                    mapa.put("pregunta1",respuesta)
                    savePhoto("fotoPregunta1")
                }
                2 -> {
                    img_question4.setImageURI(mPhotoUri);respuesta2="SI";pregunta2=2
                    Log.d("respuesta","pregunta2: $pregunta2 respuesta2:$respuesta2")
                    completo2=true
                    mapa.put("pregunta2",respuesta2)
                    savePhoto("fotoPregunta2")
                }
                3 -> {
                    img_question3.setImageURI(mPhotoUri);respuesta="NO";pregunta=1
                    Log.d("respuesta","pregunta1: $pregunta respuesta1:$respuesta")
                    completo1=true
                    mapa.put("pregunta1",respuesta)
                    savePhoto("fotoPregunta1")
                }
                4-> {
                    img_question4.setImageURI(mPhotoUri);respuesta2="NO";pregunta2=2
                    Log.d("respuesta","pregunta2: $pregunta2 respuesta2:$respuesta2")
                    completo2=true
                    mapa.put("pregunta2",respuesta2)
                    savePhoto("fotoPregunta2")
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
        } else {
            completo1=false;completo2=false
        }
        habilitarBtn()
    }
}