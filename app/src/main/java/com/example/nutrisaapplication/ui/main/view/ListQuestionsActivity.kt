package com.example.nutrisaapplication.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.sql.DatabaseMetaData

class ListQuestionsActivity : BaseActivity() {
    private lateinit var data:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_questions)
        data= FirebaseDatabase.getInstance().getReference()
        data.child("Regiones").setValue("Centro")
    }
}