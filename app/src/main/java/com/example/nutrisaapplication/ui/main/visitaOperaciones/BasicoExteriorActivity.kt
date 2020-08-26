package com.example.nutrisaapplication.ui.main.visitaOperaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.activity_basico_exterior.*

class BasicoExteriorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basico_exterior)
        checkOption()
    }

    private fun checkOption() {
        var status="VISIBLE"
        imageButton.setOnClickListener {
            if(status=="VISIBLE"){
                tv_imgbutton.visibility= View.VISIBLE
                status=""
            }else{
                tv_imgbutton.visibility= View.INVISIBLE
                status="VISIBLE"
            }
        }
        imageButton2.setOnClickListener {
            if(status=="VISIBLE"){
                tv_imgbutton2.visibility= View.VISIBLE
                status=""
            }else{
                tv_imgbutton2.visibility= View.INVISIBLE
                status="VISIBLE"
            }
        }
    }
}