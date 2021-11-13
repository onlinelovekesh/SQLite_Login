package com.example.registersql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        databaseHelper= DatabaseHelper(this)

        btnlogin.setOnClickListener {
            var uname=eteml.text.toString()
            var upass=etpass.text.toString()

            val check=databaseHelper.loginAuthentication(uname, upass)

            if (uname.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show()
            } else if (upass.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show()
            }else if (check){
                Snackbar.make(loginLayout, "Login Successful", Snackbar.LENGTH_LONG).show()

            }
            else{
                Snackbar.make(loginLayout, "Wrong username or password", Snackbar.LENGTH_LONG).show()
                    // loginlayout is the id from login xml
            }
        }

        btngotoreg.setOnClickListener {
            var i=Intent(this, Registration::class.java)
            startActivity(i)
        }

    }
}