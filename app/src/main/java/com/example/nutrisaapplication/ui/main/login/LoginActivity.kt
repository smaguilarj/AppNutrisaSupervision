package com.example.nutrisaapplication.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.login.recuperar_contraseña.RecuperarPasswordActivity
import com.example.nutrisaapplication.ui.main.supervision.SupervisionActivity
import com.example.nutrisaapplication.utils.Validations
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth
    var dataList = ArrayList<String>()
    companion object {
        const val EMIAL_INVALIDO = "no es un email valido"
        const val OBLIGATORIO="Campo obligatorio"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        println("la cadena es: " + subCadena(""))
        btn_submit.setOnClickListener {
            btn_submit.isEnabled= false
            login() }
        validation()
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        btn_reset.setOnClickListener {
            this.startActivity(Intent(this, RecuperarPasswordActivity::class.java))
        }
        Log.i("Tag", "un saludo este es un tag se vera en el logcat")
    }

    private fun validation() {
        et_user_name.setOnFocusChangeListener { view, b ->
            if(!Validations.isValidEmail(et_user_name.text.toString())) {
            et_user_name.error=EMIAL_INVALIDO
        }else{
               et_user_name.error = null
            }
        }

        et_user_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!Validations.isValidEmail(et_user_name.text.toString())) {
                    et_user_name.error = EMIAL_INVALIDO
                }
                if (et_user_name.text.toString().isEmpty()) {
                    et_user_name.error = OBLIGATORIO
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!Validations.isValidEmail(et_user_name.text.toString())) {
                    et_user_name.error = EMIAL_INVALIDO
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!Validations.isValidEmail(et_user_name.text.toString())) {
                    et_user_name.error = EMIAL_INVALIDO
                }
            }
        })
        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (et_email.getText().toString().isEmpty()) {
                    et_email.setError(OBLIGATORIO)
                } else {
                    et_email.error = null
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_email.getText().toString().isEmpty()) {
                    et_email.error = OBLIGATORIO
                } else {
                    et_email.error = null
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_email.text.toString().isEmpty()) {
                    et_email.error = OBLIGATORIO
                } else {
                    et_email.error = null
                }
            }

        })

    }

    init {
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
    }

    fun login(){
        if(!et_user_name.text.toString().isEmpty() && !et_email.text.toString().isEmpty() ){
            auth.signInWithEmailAndPassword(et_user_name.text.toString(), et_email.text.toString())
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "te conectaste correctamente", Toast.LENGTH_SHORT).show()
                        this.startActivity(
                            Intent(this, SupervisionActivity::class.java).addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                    }else{
                        Toast.makeText(
                            this,
                            "error de datos verifica tu email o contraseña",
                            Toast.LENGTH_LONG
                        ).show()
                        val error=task.getException()?.message
                        Toast.makeText(
                            this,
                            "error: $error",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    btn_submit.isEnabled=true
                }
        }else{
            Toast.makeText(this, "Ingresa datos campos vacios", Toast.LENGTH_SHORT).show()
            btn_submit.isEnabled=true
        }
    }



    fun subCadena(cadena: String):String{
        if (cadena.isNotBlank()&& cadena.isNotEmpty()){
           if(cadena.length>=3) {
               val ncadena= cadena.substring(cadena.length-3, cadena.length)
               val builder = StringBuilder(ncadena)
               val reverse=builder.reverse().toString()
               val nuevaCadena=reverse + cadena +ncadena
               println("texto reversa $builder")
               println("texto ultimos tres $ncadena")
               println("resultado: $nuevaCadena")
               return nuevaCadena
           }else{
               return "necesitas minimo 3 caracteres"
           }
        }else
           return ""
    }
   /* FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser()
    if (user != null) {
        // Name, email address, and profile photo Url
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        // Check if user's email is verified
        boolean emailVerified = user.isEmailVerified();
        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getIdToken() instead.
        String uid = user.getUid();
    }*/
}