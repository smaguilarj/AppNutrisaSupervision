package com.example.nutrisaapplication.ui.main.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Toast
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.login.recuperar_contraseña.RecuperarPasswordActivity
import com.example.nutrisaapplication.ui.main.supervision.SupervisionActivity
import com.example.nutrisaapplication.utils.Validations
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_submit.setOnClickListener {
            btn_submit.isEnabled= false
            login() }
        validation()
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN )
        btn_reset.setOnClickListener {
            this.startActivity( Intent(this, RecuperarPasswordActivity::class.java))
        }
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
        et_email.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if (et_email.getText().toString().isEmpty()) {
                    et_email.setError("campo obligatorio");
                }else{
                    et_email.error = null
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_email.getText().toString().isEmpty()) {
                    et_email.setError("campo obligatorio");
                }else{
                    et_email.error = null
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_email.getText().toString().isEmpty()) {
                    et_email.setError("campo obligatorio");
                }else{
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
            auth.signInWithEmailAndPassword(et_user_name.text.toString(),et_email.text.toString())
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "te conectaste correctamente", Toast.LENGTH_SHORT).show()
                        this.startActivity( Intent(this, SupervisionActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    }else{
                        Toast.makeText(this, "error de datos verifica tu email o contraseña", Toast.LENGTH_SHORT).show()
                    }
                    btn_submit.isEnabled=true
                }
        }else{
            Toast.makeText(this, "Ingresa datos campos vacios", Toast.LENGTH_SHORT).show()
            btn_submit.isEnabled=true
        }
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