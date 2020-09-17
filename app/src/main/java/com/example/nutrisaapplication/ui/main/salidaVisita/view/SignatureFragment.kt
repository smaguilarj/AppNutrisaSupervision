package com.example.nutrisaapplication.ui.main.salidaVisita.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_signature.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class SignatureFragment : Fragment() {

    private val navigation by lazy{
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signature, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signature_pad.setPenColor(Color.BLACK)
        cleanButton.setOnClickListener {
            signature_pad.clearView()
        }
        saveButton.setOnClickListener {
            val signature = signature_pad.signatureBitmap
            val savedPath = bitmapToFile(signature)
           /* val base64Signature= savedPath.imageEncodeBase64(savedPath)
            //Toast.makeText(requireContext(), "Firma en base64: $base64Signature", Toast.LENGTH_LONG).show()
            Log.i("firma","respuesta: $base64Signature")
            SharedApp.prefs.signature = base64Signature
            activity?.setResult(Activity.RESULT_OK)
            SIGNATURE_COMPLETE= true*/
            activity?.onBackPressed()
        }
    }
    fun viewPhoto(code: Int) {
        ImagePicker.with(this)
            .crop()                             //Crop image(Optional), Check Customization for more option
            .compress(2048)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
            .start(code)
    }

    fun bitmapToFile(bitmap: Bitmap): String {
        // Get the context wrapper
        val wrapper = ContextWrapper(activity?.applicationContext)
        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file,"saved-signature.jpg")

        try{
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e: IOException){
            e.printStackTrace()
        }
        // Return the saved bitmap uri
        //Uri.parse(file.absolutePath)
        return file.absolutePath
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //You can get File object from intent
            //val file: File = ImagePicker.getFile(data)!!
            //You can also get File Path from intent
            val filePath: String = ImagePicker.getFilePath(data)!!
            //Log.i("foto", "file: $file" + " " + "File Path: $filePath")
            when (requestCode) {
                1 -> {
                   /* imageButtonSelfie.setImageResource(R.drawable.ic_check_circle_24)
                    imageButtonSelfie.setBackgroundResource(R.color.transparent)
                    SharedApp.prefs.completeFrontIne = true
                    base64Selfi = filePath.imageEncodeBase64(filePath)
                    Log.i("foto", "fotobase64Front: $base64Selfi")
                    SharedApp.prefs.selfi = base64Selfi*/
                }
                2 -> {
                    /*imageButtonAdress.setImageResource(R.drawable.ic_check_circle_24)
                    imageButtonAdress.setBackgroundResource(R.color.transparent)
                    SharedApp.prefs.completeBackIne = true
                    base64DocAdress = filePath.imageEncodeBase64(filePath)
                    Log.i("foto", "fotobase64Front: $base64DocAdress")
                    SharedApp.prefs.photoAdress = base64DocAdress*/
                }
            }
            // Toast.makeText(requireContext(), "Se capturo la imagen correctamente", Toast.LENGTH_SHORT).show()
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            when (requestCode) {
                1 -> {
                   /* imageButtonSelfie.setImageResource(R.drawable.ic_baseline_cancel_24)
                    imageButtonSelfie.setBackgroundResource(R.color.transparent)
                    SharedApp.prefs.completeFrontIne = false*/
                }
                2 -> {
                   /* imageButtonAdress.setImageResource(R.drawable.ic_baseline_cancel_24)
                    imageButtonAdress.setBackgroundResource(R.color.transparent)
                    SharedApp.prefs.completeBackIne = false*/
                }
            }  //Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show()
        }
    }
}