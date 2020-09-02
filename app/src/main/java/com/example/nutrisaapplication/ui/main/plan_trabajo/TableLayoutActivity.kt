package com.example.nutrisaapplication.ui.main.plan_trabajo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.activity_table_layout.*

class TableLayoutActivity : AppCompatActivity() {

    val tableLayout by lazy{ TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_layout)

        val lp = TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        tableLayout.apply {
            layoutParams = lp
            isShrinkAllColumns = true
        }

        val cabecera = ArrayList<Asset>()
        cabecera.add(Asset("asdasd", "asdasdasd"))
        cabecera.add(Asset("asdasd2", "asdasdasd2"))
        cabecera.add(Asset("asdasd3", "asdasdasd3"))
        cabecera.add(Asset("asdasd4", "asdasdasd4"))
        cabecera.add(Asset("asdasd5", "asdasdasd5"))

        val data1 = ArrayList<Data1>()
        data1.add(Data1(1.00,0.00,0.00,0.00,0.00))
        data1.add(Data1(0.00,1.00,0.00,0.00,0.00))
        data1.add(Data1(0.00,0.00,1.00,0.00,0.00))
        data1.add(Data1(0.00,0.00,0.00,1.00,0.00))
        data1.add(Data1(0.00,0.00,0.00,0.00,1.00))

        val data2 = ArrayList<Data2>()
        data2.add(Data2("km/l", "km", "Comb", "h motor", "fac. de carga"))

        for(i in 0..data1.size-1){
            createTable2(cabecera, i, data1, data2)
        }
        linearLayout.addView(tableLayout)
        //createRows()
    }

    fun createCabecera(cabecera : ArrayList<Asset>, position : Int){
        val row_cabecera = TableRow(this)
        row_cabecera.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)


        val llp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT, 1f)
        llp.topMargin = 25

        val txt_numeconom = TextView(this)
        txt_numeconom.apply {
            layoutParams = llp
            text = cabecera.get(position).n1
            textSize = 15f
        }

        val llp2 = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT, 3f)
        llp2.topMargin = 25
        val txt_data = TextView(this)
        txt_data.apply {
            layoutParams = llp2
            gravity = Gravity.RIGHT
            text = cabecera.get(position).n2
            textSize = 15f
        }

        row_cabecera.addView(txt_numeconom)
        row_cabecera.addView(txt_data)

        tableLayout.addView(row_cabecera)
    }

    fun createdata1(data1: ArrayList<Data1>, position : Int){
        val row = TableRow(this)
        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        val camp1 = TextView(this)
        camp1.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(position).kml.toString()
            textSize = 15f
        }

        val camp2 = TextView(this)
        camp2.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(position).km.toString()
            textSize = 15f
        }

        val camp3 = TextView(this)
        camp3.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(position).comb.toString()
            textSize = 15f
        }

        val camp4 = TextView(this)
        camp4.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(position).hmotor.toString()
            textSize = 15f
        }

        val camp5 = TextView(this)
        camp5.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(position).faccar.toString()
            textSize = 15f
        }

        row.addView(camp1)
        row.addView(camp2)
        row.addView(camp3)
        row.addView(camp4)
        row.addView(camp5)

        tableLayout.addView(row)
    }

    fun createdata2(data1: ArrayList<Data2>){
        val row = TableRow(this)
        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        val camp1 = TextView(this)
        camp1.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(0).kml
            textSize = 15f
        }

        val camp2 = TextView(this)
        camp2.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(0).km
            textSize = 15f
        }

        val camp3 = TextView(this)
        camp3.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(0).comb
            textSize = 15f
        }

        val camp4 = TextView(this)
        camp4.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(0).hmotor
            textSize = 15f
        }

        val camp5 = TextView(this)
        camp5.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
            text = data1.get(0).faccar
            textSize = 15f
        }

        row.addView(camp1)
        row.addView(camp2)
        row.addView(camp3)
        row.addView(camp4)
        row.addView(camp5)

        tableLayout.addView(row)
    }

    fun createTable2(cabecera : ArrayList<Asset>, position : Int, data1: ArrayList<Data1>, data2 : ArrayList<Data2>) {
        createCabecera(cabecera, position)
        createdata1(data1, position)
        createdata2(data2)
    }
}

data class Asset(
    val n1: String,
    val n2 : String
)

data class Data1(
    val kml : Double,
    val km : Double,
    val comb : Double,
    val hmotor : Double,
    val faccar : Double
)

data class Data2(
    val kml : String,
    val km : String,
    val comb : String,
    val hmotor : String,
    val faccar : String
)