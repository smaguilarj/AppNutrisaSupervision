
package com.example.nutrisaapplication.ui.main.login.recuperar_contraseña

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nutrisaapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recuperar_password.*

class RecuperarPasswordActivity : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_password)
        firebaseAuth= FirebaseAuth.getInstance()
        btn_reset.setOnClickListener {
            val email= et_email.text.toString()
            if(!email.isNullOrEmpty()){
            firebaseAuth?.sendPasswordResetEmail(email)?.addOnCompleteListener { task->
                if(task.isSuccessful){
                    Toast.makeText(this, "Se envio correo a $email  accede al link del correo", Toast.LENGTH_LONG).show()
                    finish()

                }else{
                    Toast.makeText(this, "Error al enviar el correo, verifica el email", Toast.LENGTH_SHORT).show()
                }

            }
            }else{
                Toast.makeText(this, "Ingeresa correo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}