package com.example.nutrisaapplication.ui.main.basicoBarra.view

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
import kotlinx.android.synthetic.main.fragment_basico_barra.*
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton10
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton11
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton12
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton13
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton14
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton7
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton8
import kotlinx.android.synthetic.main.fragment_basico_barra.imageButton9
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na10
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na11
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na12
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na13
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na14
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na4
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na5
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na6
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na7
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na8
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_na9
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no10
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no11
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no12
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no13
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no14
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no4
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no5
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no6
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no7
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no8
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_no9
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes10
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes11
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes12
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes13
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes14
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes4
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes5
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes6
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes7
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes8
import kotlinx.android.synthetic.main.fragment_basico_barra.imb_yes9
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question10
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question11
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question12
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question13
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question14
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question4
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question5
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question6
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question7
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question8
import kotlinx.android.synthetic.main.fragment_basico_barra.img_question9
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton10
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton11
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton12
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton13
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton14
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton5
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton7
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton8
import kotlinx.android.synthetic.main.fragment_basico_barra.tv_imgbutton9
import kotlinx.android.synthetic.main.fragment_basico_interior.*
import kotlinx.android.synthetic.main.fragment_caja.*
import java.io.File

class BasicoBarraFragment : Fragment() {

    private var respuesta:String=""
    private var pregunta=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basico_barra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOption()
        checkCamara()
    }

    private fun checkCamara() {
        imb_yes14.setOnClickListener { tomaFoto(1) }
        imb_yes4.setOnClickListener { tomaFoto(2) }
        imb_yes5.setOnClickListener { tomaFoto(3) }
        imb_yes6.setOnClickListener { tomaFoto(4) }
        imb_yes7.setOnClickListener { tomaFoto(5) }
        imb_yes8.setOnClickListener { tomaFoto(6) }
        imb_yes9.setOnClickListener { tomaFoto(7) }
        imb_yes10.setOnClickListener { tomaFoto(8) }
        imb_yes11.setOnClickListener { tomaFoto(9) }
        imb_yes12.setOnClickListener { tomaFoto(10) }
        imb_yes13.setOnClickListener { tomaFoto(11) }
        imb_no14.setOnClickListener { tomaFoto(12) }
        imb_no4.setOnClickListener { tomaFoto(13) }
        imb_no5.setOnClickListener { tomaFoto(14) }
        imb_no6.setOnClickListener { tomaFoto(15) }
        imb_no7.setOnClickListener { tomaFoto(16) }
        imb_no8.setOnClickListener { tomaFoto(17) }
        imb_no9.setOnClickListener { tomaFoto(18) }
        imb_no10.setOnClickListener { tomaFoto(19) }
        imb_no11.setOnClickListener { tomaFoto(20) }
        imb_no12.setOnClickListener { tomaFoto(21) }
        imb_no13.setOnClickListener { tomaFoto(22) }
        imb_na14.setOnClickListener { pregunta=14; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na4.setOnClickListener { pregunta=15; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na5.setOnClickListener { pregunta=16; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na6.setOnClickListener { pregunta=17; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na7.setOnClickListener { pregunta=18; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na8.setOnClickListener { pregunta=19; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na9.setOnClickListener { pregunta=20; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na10.setOnClickListener { pregunta=21; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na11.setOnClickListener { pregunta=22; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na12.setOnClickListener { pregunta=23; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
        imb_na13.setOnClickListener { pregunta=24; respuesta="NA";Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")}
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
                    img_question14.setImageURI(fileUri);respuesta="SI";pregunta=14; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                2 -> {
                    img_question4.setImageURI(fileUri);respuesta="SI";pregunta=15; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                3 -> {
                    img_question5.setImageURI(fileUri);respuesta="SI";pregunta=16; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                4-> {
                    img_question6.setImageURI(fileUri);respuesta="SI";pregunta=17; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                5 -> {
                    img_question7.setImageURI(fileUri);respuesta="SI";pregunta=18; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                6 -> {
                    img_question8.setImageURI(fileUri);respuesta="SI";pregunta=19; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                7 -> {
                    img_question9.setImageURI(fileUri);respuesta="SI";pregunta=20; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                8-> {
                    img_question10.setImageURI(fileUri);respuesta="SI";pregunta=21; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                9 -> {
                    img_question11.setImageURI(fileUri);respuesta="SI";pregunta=22; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                10 -> {
                    img_question12.setImageURI(fileUri);respuesta="SI";pregunta=23; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                11 -> {
                    img_question13.setImageURI(fileUri);respuesta="SI";pregunta=24; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                12-> {
                    img_question14.setImageURI(fileUri);respuesta="NO";pregunta=14; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                13 -> {
                    img_question4.setImageURI(fileUri);respuesta="NO";pregunta=15; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                14 -> {
                    img_question5.setImageURI(fileUri);respuesta="NO";pregunta=16; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                15 -> {
                    img_question6.setImageURI(fileUri);respuesta="NO";pregunta=17; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                16-> {
                    img_question7.setImageURI(fileUri);respuesta="NO";pregunta=18; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                17 -> {
                    img_question8.setImageURI(fileUri);respuesta="NO";pregunta=19; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                18 -> {
                    img_question9.setImageURI(fileUri);respuesta="NO";pregunta=20; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                19 -> {
                    img_question10.setImageURI(fileUri);respuesta="NO";pregunta=21; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                20-> {
                    img_question11.setImageURI(fileUri);respuesta="NO";pregunta=22; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                21 -> {
                    img_question12.setImageURI(fileUri);respuesta="NO";pregunta=23; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
                }
                22 -> {
                    img_question13.setImageURI(fileUri);respuesta="NO";pregunta=24; Log.d("respuesta","pregunta: $pregunta respuesta:$respuesta")
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
            //Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkOption() {
        var status = "VISIBLE"
        var status2 = "VISIBLE"
        var status3 = "VISIBLE"
        var status4 = "VISIBLE"
        var status5 = "VISIBLE"
        var status6 = "VISIBLE"
        var status7 = "VISIBLE"
        var status8 = "VISIBLE"
        var status9 = "VISIBLE"
        var status10 = "VISIBLE"
        var status11 = "VISIBLE"

        imageButton14.setOnClickListener {
            if (status == "VISIBLE") {
                tv_imgbutton14.visibility = View.VISIBLE
                status = ""
            } else {
                tv_imgbutton14.visibility = View.INVISIBLE
                status = "VISIBLE"
            }
        }
        imageButton15.setOnClickListener {
            if (status2 == "VISIBLE") {
                tv_imgbutton15.visibility = View.VISIBLE
                status2 = ""
            } else {
                tv_imgbutton15.visibility = View.INVISIBLE
                status2 = "VISIBLE"
            }
        }
        imageButton16.setOnClickListener {
            if (status3 == "VISIBLE") {
                tv_imgbutton5.visibility = View.VISIBLE
                status3 = ""
            } else {
                tv_imgbutton5.visibility = View.INVISIBLE
                status3 = "VISIBLE"
            }
        }
        imageButton17.setOnClickListener {
            if (status4 == "VISIBLE") {
                tv_imgbutton17.visibility = View.VISIBLE; status4 = ""
            } else {
                tv_imgbutton17.visibility = View.INVISIBLE; status4 = "VISIBLE"
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
        imageButton10.setOnClickListener {
            if (status8 == "VISIBLE") {
                tv_imgbutton10.visibility = View.VISIBLE; status8 = ""
            } else {
                tv_imgbutton10.visibility = View.INVISIBLE; status8 = "VISIBLE"
            }
        }
        imageButton11.setOnClickListener {
            if (status9 == "VISIBLE") {
                tv_imgbutton11.visibility = View.VISIBLE; status9 = ""
            } else {
                tv_imgbutton11.visibility = View.INVISIBLE; status9 = "VISIBLE"
            }
        }
        imageButton12.setOnClickListener {
            if (status10 == "VISIBLE") {
                tv_imgbutton12.visibility = View.VISIBLE; status10 = ""
            } else {
                tv_imgbutton12.visibility = View.INVISIBLE; status10 = "VISIBLE"
            }
        }
        imageButton13.setOnClickListener {
            if (status11 == "VISIBLE") {
                tv_imgbutton13.visibility = View.VISIBLE; status11 = ""
            } else {
                tv_imgbutton13.visibility = View.INVISIBLE; status11 = "VISIBLE"
            }
        }
       /* imageButton14.setOnClickListener {
            if (status12 == "VISIBLE") {
                tv_imgbutton14.visibility = View.VISIBLE; status12 = ""
            } else {
                tv_imgbutton14.visibility = View.INVISIBLE; status12 = "VISIBLE"
            }
        }*/
    }

}