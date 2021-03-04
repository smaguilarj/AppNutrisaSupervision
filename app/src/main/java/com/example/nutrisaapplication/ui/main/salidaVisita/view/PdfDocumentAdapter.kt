package com.example.nutrisaapplication.ui.main.salidaVisita.view

import android.content.Context
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.util.Log
import java.io.*

class PdfDocumentAdapter(context: Context?, path: String) : PrintDocumentAdapter() {
    internal var context:Context?=null
    internal var path=""
    var ejemplo:String? = ""

    init {
        this.context=context
        this.path=path
        ejemplo= "Laura"
    }

    override fun onLayout(
        p0: PrintAttributes?,
        p1: PrintAttributes?,
        cancel: CancellationSignal?,
        layoutResultCallback: LayoutResultCallback?,
        p4: Bundle?
    ) {
        if (cancel != null) {
            if (cancel.isCanceled)
                layoutResultCallback?.onLayoutCancelled()
            else {
                val builder = PrintDocumentInfo.Builder("Reporte Nutrisa")
                builder.setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(PrintDocumentInfo.PAGE_COUNT_UNKNOWN)
                    .build()
                layoutResultCallback?.onLayoutFinished(builder.build(), p0 != p1)
            }
        }
    }

    override fun onWrite(
        pageRange: Array<out PageRange>?,
        parcelFile: ParcelFileDescriptor?,
        cancellationSignal: CancellationSignal?,
        writeResultCallback: WriteResultCallback?
    ) {
        var ine: InputStream? = null
        var out: OutputStream? = null

        try {
            val file= File(path)
            ine=FileInputStream(file)
            out= FileOutputStream(parcelFile?.fileDescriptor)
            if(!cancellationSignal!!.isCanceled){
                ine.copyTo(out)
                writeResultCallback?.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
            }
            else
                writeResultCallback?.onWriteCancelled()
        }catch (e: Exception){
            writeResultCallback?.onWriteFailed(e.message)
            Log.e("pdf",e.message)
        }finally {
            try {
                ine!!.close()
                out!!.close()
            }catch (e: IOException){
                Log.e("pdf",e.message)
            }
        }
    }

}
