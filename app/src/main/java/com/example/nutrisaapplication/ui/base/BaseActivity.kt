package com.example.nutrisaapplication.ui.base

import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.nutrisaapplication.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


open class BaseActivity : AppCompatActivity() {
    companion object {
        var showSessionMessage = false
        var isLogged = false
    }

    /*private val baseViewModel by lazy {
        ViewModelProvider(this).get(BaseViewModel::class.java)
    }

    private val loader by lazy {
        LoaderDialog(this)
    }

    private val observerLogout = Observer<Resource<Boolean>> {
        hideProgressLoader()
        when (it.status) {
            Status.SUCCESS,
            Status.ERROR -> {
                goToMainActivity()
            }
            Status.LOADING -> {
                showProgressLoader()
            }
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //retrieveResponseServices()
       /* AppCenter.start(
            application, "ba2c3137-be9b-47bf-a2fa-92cd226e3e5c",
            Analytics::class.java, Crashes::class.java
        )*/
    }

   /* override fun onDestroy() {
        super.onDestroy()
        App.instance.mixpanelHelper.flush()
    }

    private fun retrieveResponseServices() {
        baseViewModel.logout.observe(this, observerLogout)
    }


    fun logout() {
        baseViewModel.logout()
    }

    fun goToMainActivity() {
        App.instance.sessionHelper.logout()
        val intent = Intent(applicationContext, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }*/

    fun changeActivity(activity: Class<*>, args: List<Pair<String, String>> = emptyList()) {
        val intent = Intent(this, activity)
        args.forEach { intent.putExtra(it.first, it.second) }
        startActivity(intent)
    }

    fun popActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun onRequestChangeFragment(
        fragment: Fragment,
        containerView: Int
    ) {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(containerView, fragment)
        ft.commit()
      /*  val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerView, fragment).commit()*/
    }

    fun shareIntent(typeShare: String, title: String, textBody: String?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = typeShare
            putExtra(Intent.EXTRA_TITLE, title)
            putExtra(Intent.EXTRA_TEXT, textBody)
        }

        val shareIntent = Intent.createChooser(sendIntent, title)
        startActivity(shareIntent)
    }

    /**
     * Take ScreenShot of a ConstraintLayout and Save in Pictures
     */
  /*  fun saveScreenShot(view: ConstraintLayout) {
        // Get Bitmap From ConstrainstLayout
        val bitmap = getBitmapFromView(view)
        bitmap?.let { saveImage(it, this, "Sabadell_transfer_") }
    }*/

    private fun getBitmapFromView(view: View): Bitmap? { //Define a bitmap with the same size as the view
        val returnedBitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null) //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas) else  //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
        return returnedBitmap
    }

    // Save Image en Pictures
    /*private fun saveImage(bitmap: Bitmap, context: Context, nameImage: String) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            val values = contentValues()
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$nameImage")
            values.put(MediaStore.Images.Media.IS_PENDING, true)
            // RELATIVE_PATH and IS_PENDING are introduced in API 29.

            val uri: Uri? =
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                saveImageToStream(bitmap, context.contentResolver.openOutputStream(uri))
                values.put(MediaStore.Images.Media.IS_PENDING, false)
                context.contentResolver.update(uri, values, null, null)
            }
            uri?.let { sendIntent(it, "image/png") }

        } else {
            val directory =
                File(Environment.getExternalStorageDirectory().toString() + "/" + "Pictures")
            // getExternalStorageDirectory is deprecated in API 29

            if (!directory.exists()) {
                directory.mkdirs()
            }
            val fileName = nameImage + System.currentTimeMillis().toString() + ".png"
            val file = File(directory, fileName)
            saveImageToStream(bitmap, FileOutputStream(file))
            val values = contentValues()
            values.put(MediaStore.Images.Media.DATA, file.absolutePath)
            // .DATA is deprecated in API 29
            val uri =
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            uri?.let { sendIntent(it, "image/png") }
        }
    }
*/
    private fun contentValues(): ContentValues {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        return values
    }

    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendIntent(uri: Uri, type: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.type = type
        startActivity(intent)
    }
}
