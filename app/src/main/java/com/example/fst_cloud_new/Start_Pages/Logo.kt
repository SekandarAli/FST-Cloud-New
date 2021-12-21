package com.example.fst_cloud_new.Start_Pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.fst_cloud_new.ADMIN.AdminPanel
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SIGN_IN.FST_Vendor_Signin

class Logo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        supportActionBar?.hide()

        val logo: ImageView = findViewById(R.id.start_logo)

//        logo.setOnLongClickListener{
//
//            intent = Intent(this,AdminPanel::class.java)
//
//            startActivity(intent)
//            return@setOnLongClickListener true
//        }

        val anim = AnimationUtils.loadAnimation(this,R.anim.anim)

        logo.startAnimation(anim)

//        logo.animate().apply {
//            duration = 8000
//            rotationBy(360f)
//
//        }.start()

            Handler().postDelayed({


                val intent = Intent(this, FSTRegisterPage::class.java)
                startActivity(intent)
                finish()
            }, 1200)


//
//        logo.setOnClickListener {
//
//
//            intent = Intent(this,FSTRegisterPage::class.java)
//            startActivity(intent)
//        }
        }

    }
