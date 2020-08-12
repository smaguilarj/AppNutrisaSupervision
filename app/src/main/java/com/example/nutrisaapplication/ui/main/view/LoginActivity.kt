package com.example.nutrisaapplication.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doBeforeTextChanged
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.utils.Validations
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_submit.setOnClickListener { login() }
        validation()
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
    }

    private fun validation() {

        et_user_name.setOnFocusChangeListener { view, b ->
            if(!Validations.isValidEmail(et_user_name.text.toString())) {
            et_user_name.error="no es un email valido"
            et_user_name.setError("no es un email valido")
        }else{
                et_user_name.error = null
            }
        }

        et_user_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(!Validations.isValidEmail(et_user_name.text.toString())) {
                    et_user_name.error="no es un email valido"
                }
                if (et_user_name.text.toString().isEmpty()) {
                    et_user_name.setError("campo obligatorio");
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!Validations.isValidEmail(et_user_name.text.toString())) {
                    et_user_name.error="no es un email valido"
                    et_user_name.setError("no es un email valido")
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               if(!Validations.isValidEmail(et_user_name.text.toString())) {
                   et_user_name.error="no es un email valido"
                   et_user_name.setError("no es un email valido")
               }
            }
        })
        et_password.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if (et_password.getText().toString().isEmpty()) {
                    et_password.setError("campo obligatorio");
                }else{
                    et_password.error = null
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_password.getText().toString().isEmpty()) {
                    et_password.setError("campo obligatorio");
                }else{
                    et_password.error = null
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_password.getText().toString().isEmpty()) {
                    et_password.setError("campo obligatorio");
                }else{
                    et_password.error = null
                }
            }

        })

    }

    init {
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
    }
    fun login(){
        if(!et_user_name.text.toString().isEmpty() && !et_password.text.toString().isEmpty() ){
            auth.signInWithEmailAndPassword(et_user_name.text.toString(),et_password.text.toString())
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "te conectaste correctamente", Toast.LENGTH_SHORT).show()
                        this.startActivity( Intent(this, SupervisionActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    }else{
                        Toast.makeText(this, "error de datos verifica tu email o contraseña", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this, "Ingresa datos campos vacios", Toast.LENGTH_SHORT).show()
        }
    }
}