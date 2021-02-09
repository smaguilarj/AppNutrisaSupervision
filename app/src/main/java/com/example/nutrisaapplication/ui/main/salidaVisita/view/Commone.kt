package com.example.nutrisaapplication.ui.main.salidaVisita.view

import android.content.Context
import com.example.nutrisaapplication.R
import java.io.File

object Commone {
fun getAppPath(context: Context):String{
    val dir= File(android.os.Environment.getExternalStorageDirectory().toString()
            + File.separator
            + context.resources.getString(R.string.directorio)
            + File.separator)
    if (!dir.exists())
        dir.mkdir()
        return dir.path+File.separator
}
}
