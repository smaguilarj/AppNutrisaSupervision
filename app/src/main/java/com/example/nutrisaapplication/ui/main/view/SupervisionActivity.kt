package com.example.nutrisaapplication.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutrisaapplication.R
import kotlinx.android.synthetic.main.activity_supervision.*

class SupervisionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervision)
        img_visita_rapida.setOnClickListener {
            this.startActivity( Intent(this, HomeActivity::class.java))
        }
    }
}