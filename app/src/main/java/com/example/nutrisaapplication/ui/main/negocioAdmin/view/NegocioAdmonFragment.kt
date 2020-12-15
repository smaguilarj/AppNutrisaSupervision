package com.example.nutrisaapplication.ui.main.negocioAdmin.view

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
import kotlinx.android.synthetic.main.fragment_negocio_admon.*
import kotlinx.android.synthetic.main.fragment_negocio_admon.imageButton5
import kotlinx.android.synthetic.main.fragment_negocio_admon.imageButton6
import kotlinx.android.synthetic.main.fragment_negocio_admon.imageButton7
import kotlinx.android.synthetic.main.fragment_negocio_admon.imageButton8
import kotlinx.android.synthetic.main.fragment_negocio_admon.imageButton9
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_na5
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_na6
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_na7
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_na8
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_na9
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_no5
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_no6
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_no7
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_no8
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_no9
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_opcional5
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_opcional6
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_opcional7
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_opcional8
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_opcional9
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_yes5
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_yes6
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_yes7
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_yes8
import kotlinx.android.synthetic.main.fragment_negocio_admon.imb_yes9
import kotlinx.android.synthetic.main.fragment_negocio_admon.img_question5
import kotlinx.android.synthetic.main.fragment_negocio_admon.img_question6
import kotlinx.android.synthetic.main.fragment_negocio_admon.img_question7
import kotlinx.android.synthetic.main.fragment_negocio_admon.img_question8
import kotlinx.android.synthetic.main.fragment_negocio_admon.img_question9
import kotlinx.android.synthetic.main.fragment_negocio_admon.tv_imgbutton5
import kotlinx.android.synthetic.main.fragment_negocio_admon.tv_imgbutton6
import kotlinx.android.synthetic.main.fragment_negocio_admon.tv_imgbutton7
import kotlinx.android.synthetic.main.fragment_negocio_admon.tv_imgbutton8
import kotlinx.android.synthetic.main.fragment_negocio_admon.tv_imgbutton9
import java.io.File


class NegocioAdmonFragment : Fragment() {

    private var respuesta:String=""
    private var pregunta=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_negocio_admon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOption()
        checkCamara()
        buttonEnviar_negocio.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun checkCamara() {
        imb_yes60.setOnClickListener { tomaFoto(1); img_question60.visibility = View.VISIBLE }
        imb_yes61.setOnClickListener { pregunta=61; respuesta="SI";img_question61.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_yes5.setOnClickListener { pregunta=62; respuesta="SI";img_question5.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_yes6.setOnClickListener { pregunta=63; respuesta="SI";img_question6.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_yes7.setOnClickListener { pregunta=64; respuesta="SI";img_question7.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_yes8.setOnClickListener { pregunta=60; respuesta="SI";img_question8.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_yes9.setOnClickListener { pregunta=60; respuesta="SI";img_question9.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_no60.setOnClickListener { tomaFoto(2); img_question60.visibility = View.VISIBLE }
        imb_no61.setOnClickListener { pregunta=61; respuesta="NO";img_question61.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_no5.setOnClickListener { pregunta=62; respuesta="NO";img_question5.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_no6.setOnClickListener { pregunta=63; respuesta="NO";img_question6.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_no7.setOnClickListener { pregunta=64; respuesta="NO";img_question7.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_no8.setOnClickListener { pregunta=65; respuesta="NO";img_question8.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_no9.setOnClickListener { pregunta=66; respuesta="NO";img_question9.visibility = View.VISIBLE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta") }
        imb_opcional61.setOnClickListener { tomaFoto(3); img_question61.visibility = View.VISIBLE }
        imb_opcional5.setOnClickListener { tomaFoto(4);img_question5.visibility = View.VISIBLE }
        imb_opcional6.setOnClickListener { tomaFoto(5);img_question6.visibility = View.VISIBLE }
        imb_opcional7.setOnClickListener { tomaFoto(6);img_question7.visibility = View.VISIBLE }
        imb_opcional8.setOnClickListener { tomaFoto(7);img_question8.visibility = View.VISIBLE }
        imb_opcional9.setOnClickListener { tomaFoto(8);img_question9.visibility = View.VISIBLE }
        imb_na60.setOnClickListener { pregunta=60; respuesta="NA"; img_question60.visibility=View.GONE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na61.setOnClickListener { pregunta=61; respuesta="NA";img_question61.visibility=View.GONE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na5.setOnClickListener { pregunta=62; respuesta="NA";img_question5.visibility=View.GONE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na6.setOnClickListener { pregunta=63; respuesta="NA";img_question6.visibility=View.GONE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na7.setOnClickListener { pregunta=64; respuesta="NA";img_question7.visibility=View.GONE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na8.setOnClickListener { pregunta=65; respuesta="NA";img_question8.visibility=View.GONE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na9.setOnClickListener { pregunta=66; respuesta="NA";img_question9.visibility=View.GONE;Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
    }

    private fun checkOption() {
        var status = "VISIBLE"
        var status2 = "VISIBLE"
        var status3 = "VISIBLE"
        var status4 = "VISIBLE"
        var status5 = "VISIBLE"
        var status6 = "VISIBLE"
        var status7 = "VISIBLE"

        imageButton60.setOnClickListener {
            if (status == "VISIBLE") {
                tv_imgbutton60.visibility = View.VISIBLE
                status = ""
            } else {
                tv_imgbutton60.visibility = View.INVISIBLE
                status = "VISIBLE"
            }
        }
        imageButton61.setOnClickListener {
            if (status2 == "VISIBLE") {
                tv_imgbutton61.visibility = View.VISIBLE
                status2 = ""
            } else {
                tv_imgbutton61.visibility = View.INVISIBLE
                status2 = "VISIBLE"
            }
        }
        imageButton5.setOnClickListener {
            if (status3 == "VISIBLE") {
                tv_imgbutton5.visibility = View.VISIBLE
                status3 = ""
            } else {
                tv_imgbutton5.visibility = View.INVISIBLE
                status3 = "VISIBLE"
            }
        }
        imageButton6.setOnClickListener {
            if (status4 == "VISIBLE") {
                tv_imgbutton6.visibility = View.VISIBLE; status4 = ""
            } else {
                tv_imgbutton6.visibility = View.INVISIBLE; status4 = "VISIBLE"
            }
        }
        imageButton7.setOnClickListener {
            if (status5 == "VISIBLE") {
                tv_imgbutton7.visibility = View.VISIBLE; status5 = ""
            } else {
                tv_imgbutton7.visibility = View.INVISIBLE; status5 = "VISIBLE"
            }
        }
        imageButton8.setOnClickListener {
            if (status6 == "VISIBLE") {
                tv_imgbutton8.visibility = View.VISIBLE; status6 = ""
            } else {
                tv_imgbutton8.visibility = View.INVISIBLE; status6 = "VISIBLE"
            }
        }
        imageButton9.setOnClickListener {
            if (status7 == "VISIBLE") {
                tv_imgbutton9.visibility = View.VISIBLE; status7 = ""
            } else {
                tv_imgbutton9.visibility = View.INVISIBLE; status7 = "VISIBLE"
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
            when (requestCode) {
                1 -> {
                    img_question60.setImageURI(fileUri);respuesta="SI";pregunta=60; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                2 -> {
                    img_question60.setImageURI(fileUri);respuesta="NO";pregunta=60; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                3 -> {
                    img_question61.setImageURI(fileUri);respuesta="SI";pregunta=61; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                4 -> {
                    img_question5.setImageURI(fileUri);respuesta="SI";pregunta=62; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                5-> {
                    img_question6.setImageURI(fileUri);respuesta="SI";pregunta=63; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                6 -> {
                    img_question7.setImageURI(fileUri);respuesta="SI";pregunta=64; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                7 -> {
                    img_question8.setImageURI(fileUri);respuesta="SI";pregunta=64; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                8 -> {
                    img_question9.setImageURI(fileUri);respuesta="SI";pregunta=65; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
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