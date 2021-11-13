package com.example.registersql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {


    private lateinit var databaseHelper: DatabaseHelper //database class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        databaseHelper = DatabaseHelper(this)

        btnsave.setOnClickListener {
            var name1=et1.text.toString()
            var email1=et2.text.toString()
            var pass1=et3.text.toString()
            var pass2=et4.text.toString()

            val u1=userdata(name = name1, email = email1, pass = pass1)

            when {
                name1.trim().isEmpty() -> {       // if (check(email))
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
                }
                email1.trim().isEmpty() -> {
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show()
                }
                pass1.trim().isEmpty() -> {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show()
                }
                pass2.trim().isEmpty() -> {
                    Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_LONG).show()
                }
                pass1!=pass2 -> {
                    Toast.makeText(this, "Password and confirm password are not same", Toast.LENGTH_LONG).show()
                }
                else -> {
                    databaseHelper.addUser(u1)
                    Snackbar.make(registrationLayout, "Great! $name1, Registration Successful!", Snackbar.LENGTH_LONG).show()
                    //Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show()
                    // automatically move to login page after 1.5 seconds
                    Handler(Looper.getMainLooper()).postDelayed({
                        var i=Intent(this, Login::class.java)
                        startActivity(i)
                    },1500)

                }
            }


        }


        btngotologin!!.setOnClickListener {
            var i=Intent(this, Login::class.java)
            startActivity(i)
        }

    }

}