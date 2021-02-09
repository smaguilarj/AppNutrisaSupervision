package com.example.nutrisaapplication.ui.main.salidaVisita.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.gkemon.XMLtoPDF.PdfGenerator
import com.gkemon.XMLtoPDF.PdfGeneratorListener
import com.gkemon.XMLtoPDF.model.FailureResponse
import com.gkemon.XMLtoPDF.model.SuccessResponse
import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_salida_visita_rapida.*
import java.io.File
import java.io.FileOutputStream


class SalidaVisitaRapidaFragment : Fragment() {

    val navigation by lazy {
        findNavController()
    }
    val file_name = "Reporte Nutrisa.pdf"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salida_visita_rapida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_firma_evaluador.setOnClickListener {
            navigation.navigate(R.id.action_salidaVisitaRapidaFragment_to_signatureFragment)
            //startActivity(Intent(requireContext(),SignatureFragment::class.java))
        }
        edtResponsable.setOnClickListener {
            navigation.navigate(R.id.action_salidaVisitaRapidaFragment_to_signatureFragment)
            //startActivity(Intent(requireContext(),SignatureFragment::class.java))
        }
        btn_salida_rapida.setOnClickListener {
            Dexter.withActivity(activity).withPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(
                object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        createPDFFile(activity?.let { it1 -> Commone.getAppPath(it1) } + file_name)
                    }

                    override fun onPermissionDenied(permiso: PermissionDeniedResponse?) {
                        if (permiso!!.isPermanentlyDenied) {
                            // navigate user to app settings
                            showSettingsDialog()
                        }
                        Log.i("pdf", "NO se va generar el PDF si no acepta los permisos")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        token: PermissionToken?
                    ) {
                        token?.continuePermissionRequest()
                        Log.i("pdf", "Requiere volver a presentar permisos")
                    }

                }).check()

            SharedApp.prefs.visitaRapida = false
            SharedApp.prefs.fachada=false
            SharedApp.prefs.piso=false
            SharedApp.prefs.barras=false
            SharedApp.prefs.caja=false
            SharedApp.prefs.bodega=false
            SharedApp.prefs.plan=false
            SharedApp.prefs.borrarLista=false
            //crearPDF()
        }

    }
    private fun showSettingsDialog() {
        val builder = android.app.AlertDialog.Builder(activity)
        builder.setTitle("Necesita permisos")
        builder.setMessage("Esta aplicación necesita permiso para crear el PDF y terminar el proceso. Puede otorgarlos en la configuración de la aplicación.")
        builder.setPositiveButton("IR A CONFIGURACIÓN",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
                openSettings()
            })
        builder.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    private fun createPDFFile(path: String) {
        if(File(path).exists())
            File(path).delete()
            try {
                val document = Document()
                //save
                PdfWriter.getInstance(document, FileOutputStream(path))
                //open write
                document.open()
                document.addCreationDate()
                document.addAuthor("Herdez Nutrisa")
                document.addCreator("Antonio Acevedo")

                val colorAccent = BaseColor(R.color.colorAccent)
                val headingSize = 20.0f
                val valueFontSize= 26.0f
                val fontName = BaseFont.createFont(
                    "assets/font/roboto_medium_italic.ttf",
                    "UTF-8",
                    BaseFont.EMBEDDED
                )

                val titleStyle= Font(fontName, 36.0f, Font.NORMAL, BaseColor.BLACK)
                addNewItem(document, "Nutrisa Reporte", Element.ALIGN_CENTER, titleStyle)
                val headingStyle= Font(fontName, headingSize, Font.NORMAL, colorAccent)
                addNewItem(document, "Menu izquierda", Element.ALIGN_LEFT, headingStyle)
                val valueStyle= Font(fontName, valueFontSize, Font.NORMAL, BaseColor.BLACK)
                addNewItem(document, "#87768", Element.ALIGN_CENTER, valueStyle)
                addLineGenerator(document)
                addNewItem(document, "Order date", Element.ALIGN_CENTER, valueStyle)
                addNewItem(document, "15/08/2021", Element.ALIGN_CENTER, headingStyle)
                addNewItem(document, "Prueba1", Element.ALIGN_CENTER, headingStyle)
                val valueStyle2= Font(fontName, valueFontSize, Font.NORMAL, BaseColor.GREEN)
                addNewItem(document, "Se guardo correctamente el PDF", Element.AUTHOR, valueStyle2)
                document.close()
                Toast.makeText(requireContext(), "documento guardado", Toast.LENGTH_LONG).show()
                activity?.onBackPressed()
                printPDF()
            }catch (e: Exception){
                Toast.makeText(requireContext(), "error: +$e", Toast.LENGTH_LONG).show()
                Log.i("pdf",e.message)
            }
    }

    private fun printPDF() {
        val printManager = activity?.getSystemService(Context.PRINT_SERVICE) as PrintManager
        try {
            val printAdapter= PdfDocumentAdapter(activity, Commone.getAppPath(activity?.applicationContext!!) + file_name
            )
            printManager.print("Document", printAdapter, PrintAttributes.Builder().build())
        }catch (e: Exception){
            Log.e("pdf", e.message!!)
            activity?.onBackPressed()
        }
    }

    @Throws(DocumentException::class)
    private fun addLineGenerator(document: Document) {
        val lineSeparator= LineSeparator()
        lineSeparator.lineColor= BaseColor(R.color.colorBlackText)
        addLineSpace(document)
        document.add(Chunk(lineSeparator))
        addLineSpace(document)
    }
    @Throws(DocumentException::class)
    private fun addLineSpace(document: Document) {
        document.add(Paragraph(""))
    }

    @Throws(DocumentException::class)
    private fun addNewItem(document: Document, text: String, align: Int, style: Font) {
      val chunk= Chunk(text, style)
        val p = Paragraph(chunk)
        p.alignment=align
        document.add(p)
    }

    private fun crearPDF() {
        PdfGenerator.getBuilder()
                //.setFolderName(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .setContext(context)
            .fromLayoutXMLSource()
            .fromLayoutXML(R.layout.fragment_barra, R.layout.fragment_salida_visita_rapida) /* "fromLayoutXML()" takes array of layout resources.
			 * You can also invoke "fromLayoutXMLList()" method here which takes list of layout resources instead of array. */
            .setDefaultPageSize(PdfGenerator.PageSize.A4) /* It takes default page size like A4,A5. You can also set custom page size in pixel
			 * by calling ".setCustomPageSize(int widthInPX, int heightInPX)" here. */
            .setFileName("Tienda10_PDF") /* It is file name */
            .setFolderName("NutrisaPDF") /* It is folder name. If you set the folder name like this pattern (FolderA/FolderB/FolderC), then
			 * FolderA creates first.Then FolderB inside FolderB and also FolderC inside the FolderB and finally
			 * the pdf file named "Test-PDF.pdf" will be store inside the FolderB. */
            .openPDFafterGeneration(true) /* It true then the generated pdf will be shown after generated. */
            .build(object : PdfGeneratorListener() {
                override fun onFailure(failureResponse: FailureResponse) {
                    super.onFailure(failureResponse)
                    /* If pdf is not generated by an error then you will findout the reason behind it
				 * from this FailureResponse. */
                    Toast.makeText(
                        context,
                        "Ocurrio un error  $failureResponse",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun showLog(log: String) {
                    super.showLog(log)
                    /*It shows logs of events inside the pdf generation process*/
                    Toast.makeText(
                        context,
                        "log  $log",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onSuccess(response: SuccessResponse) {
                    super.onSuccess(response)
                    Toast.makeText(
                        context,
                        "respuesta:  ${response.path}",
                        Toast.LENGTH_SHORT
                    ).show()
                    /* If PDF is generated successfully then you will find SuccessResponse
				 * which holds the PdfDocument,File and path (where generated pdf is stored)*/
                }
            })
    }
}