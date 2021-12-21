package com.example.fst_cloud_new.PROFILES

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.Start_Pages.FSTForgetpassword
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Show_Data

class Setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        val forget_pass: Button = findViewById(R.id.forget_pass)



        forget_pass.setOnClickListener(View.OnClickListener {

            intent = Intent(this, FSTForgetpassword::class.java)
            startActivity(intent)
        })

    }
}