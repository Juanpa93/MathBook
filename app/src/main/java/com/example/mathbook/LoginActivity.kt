package com.example.mathbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)


        var datosRecibidos = intent.extras
        var correo = datosRecibidos?.getString("correo")
        var contrasena = datosRecibidos?.getString("contraseña")

        bt_registrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

        bt_iniciarSesion.setOnClickListener {
            if (correo == et_correoLogin.text.toString() && contrasena == et_passwordLogin.text.toString()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("correo", et_correoLogin.text.toString())
                startActivity(intent)
                finish()
            }
            if (correo != et_correoLogin.text.toString()) {
                Toast.makeText(this, "Correo no coincide", Toast.LENGTH_SHORT).show()
            }
            if (contrasena != et_passwordLogin.text.toString()) {
                Toast.makeText(this, "Contraseña no coincide", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
