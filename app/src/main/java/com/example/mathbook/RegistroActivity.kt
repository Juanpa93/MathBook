package com.example.mathbook

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private var fecha : String = ""
    private var cal = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_registro)


        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "dd/MM/yyyy"
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()
                tv_fecha_nacimiento.text = fecha
            }
        }

        ib_calendario.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        bt_guardar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val telefono = et_telefono.text.toString()
            val contrasena = et_password.text.toString()
            val repcontrasena = et_reppassword.text.toString()
            val ciudad = sp_ciudades.selectedItem.toString()


            if (nombre.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty() && contrasena.isNotEmpty() && ciudad != "Seleccione una ciudad" && fecha.isNotEmpty()) {
                if(contrasena.length < 6){
                    Toast.makeText(this, "La contraseña debe contener minimo 6 caracteres", Toast.LENGTH_SHORT).show()
                }
                if (contrasena == repcontrasena && contrasena.length >= 6) {

                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("correo", et_correo.text.toString())
                    intent.putExtra("contraseña",et_password.text.toString())
                    startActivity(intent)
                    finish()
                } else {
                    if(contrasena != repcontrasena){
                    Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
                }
            } else {
                Toast.makeText(this, "Datos incompletos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

