package com.example.fst_cloud_new.ADMIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.fst_cloud_new.R

class AdiminLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adimin_login)

        val login : Button = findViewById(R.id.admin_login)
        val signinemail : EditText = findViewById(R.id.adminemail)
        val signinpassword : EditText = findViewById(R.id.adminpassword)


        login.setOnClickListener {

            if(signinemail.text.toString() != "admin")
            {
                signinemail?.error ="Email is not true"
            }

            else if(signinpassword.text.toString() != "123")
            {
                signinpassword?.error ="Password is not true"
            }

            else(signinemail.text.toString() == "admin" && signinpassword.text.toString() == "123")

                var intent = Intent(this,AdminPanel::class.java)
                startActivity(intent)



        }


    }
}