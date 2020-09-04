package com.example.nutrisaapplication.ui.main.basicoExterior

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
        var status2="VISIBLE"
        imageButton3.setOnClickListener {
            if(status=="VISIBLE"){
                tv_imgbutton3.visibility= View.VISIBLE
                status=""
            }else{
                tv_imgbutton3.visibility= View.INVISIBLE
                status="VISIBLE"
            }
        }
        imageButton4.setOnClickListener {
            if(status2=="VISIBLE"){
                tv_imgbutton4.visibility= View.VISIBLE
                status2=""
            }else{
                tv_imgbutton4.visibility= View.INVISIBLE
                status2="VISIBLE"
            }
        }
    }
}