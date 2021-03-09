package com.example.nutrisaapplication.ui.main.basicoExterior

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.nutrisaapplication.R
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_basico_exterior.*
import kotlinx.android.synthetic.main.activity_basico_exterior.imageButton1
import kotlinx.android.synthetic.main.activity_basico_exterior.imageButton2
import kotlinx.android.synthetic.main.activity_basico_exterior.tv_imgbutton1
import kotlinx.android.synthetic.main.activity_basico_exterior.tv_imgbutton2
import kotlinx.android.synthetic.main.fragment_basico_exterior.imb_na1
import kotlinx.android.synthetic.main.fragment_basico_exterior.imb_na2
import kotlinx.android.synthetic.main.fragment_basico_exterior.imb_no2
import kotlinx.android.synthetic.main.fragment_basico_exterior.imb_yes1
import kotlinx.android.synthetic.main.fragment_basico_exterior.imb_yes4
import kotlinx.android.synthetic.main.fragment_basico_exterior.img_question1
import kotlinx.android.synthetic.main.fragment_basico_exterior.img_question2
import java.io.File

class BasicoExteriorActivity : AppCompatActivity() {

    private var respuesta:String=""
    private var pregunta=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basico_exterior)
        checkOption()
        imb_yes1.setOnClickListener { tomaFoto(1) }
        imb_yes4.setOnClickListener { tomaFoto(2) }
        imb_no1.setOnClickListener { tomaFoto(3) }
        imb_no2.setOnClickListener { tomaFoto(4) }
        imb_na1.setOnClickListener { pregunta=1; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na2.setOnClickListener { pregunta=2; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        buttonEnviar_exterior.setOnClickListener {
            onBackPressed()
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
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            //Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkOption() {
        var status="VISIBLE"
        var status2="VISIBLE"
        imageButton1.setOnClickListener {
            if(status=="VISIBLE"){
                tv_imgbutton1.visibility= View.VISIBLE
                status=""
            }else{
                tv_imgbutton1.visibility= View.INVISIBLE
                status="VISIBLE"
            }
        }
        imageButton2.setOnClickListener {
            if(status2=="VISIBLE"){
                tv_imgbutton2.visibility= View.VISIBLE
                status2=""
            }else{
                tv_imgbutton2.visibility= View.INVISIBLE
                status2="VISIBLE"
            }
        }
    }


}