package com.example.fst_cloud_new.Start_Pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.fst_cloud_new.ADMIN.AdminPanel
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.SIGN_IN.FSTSignIn
import com.example.fst_cloud_new.SIGN_IN.FST_Vendor_Signin
import com.example.fst_cloud_new.SIGN_UP.FSTSignUp
import com.example.fst_cloud_new.SIGN_UP.FST_Vendor_Signup

class FSTRegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fstregister_page)

        supportActionBar?.hide()

        val continue_user : Button = findViewById(R.id.continue_user)
        val continue_vendor : Button = findViewById(R.id.continue_vendor)
        val next : Button = findViewById(R.id.next)
        val logo2 : ImageView = findViewById(R.id.start2_logo)


//        logo2.animate().apply {
//            duration = 8000
//            rotationBy(360f)
//
//        }.withEndAction {
//
//            logo2.animate().apply {
//                duration = 8000
//                rotationBy(360f)
//            }.start()
//        }

        logo2.setOnLongClickListener{

            intent = Intent(this, AdminPanel::class.java)

            startActivity(intent)
            return@setOnLongClickListener true
        }

        continue_user.setOnClickListener(View.OnClickListener {

            intent = Intent(this, FSTSignIn::class.java)
            startActivity(intent)
        })


        continue_vendor.setOnClickListener(View.OnClickListener {

            intent = Intent(this, FST_Vendor_Signin::class.java)
            startActivity(intent)
        })

        next.setOnClickListener(View.OnClickListener {

            intent = Intent(this, HOMEPAGE::class.java)
            startActivity(intent)
        })
    }
}