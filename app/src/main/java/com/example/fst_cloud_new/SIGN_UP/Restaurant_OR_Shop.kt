package com.example.fst_cloud_new.SIGN_UP

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
import com.example.fst_cloud_new.Vendor_Resturant.Vendor_Resturant_Add_Data
import com.example.fst_cloud_new.Vendor_Shop.Vendor_Shop_Add_Data

class Restaurant_OR_Shop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_or_shop)

        supportActionBar?.hide()

        val restaurant : Button = findViewById(R.id.restaurant)
        val shop : Button = findViewById(R.id.shop)



        restaurant.setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Resturant_Add_Data::class.java)
            startActivity(intent)
        })


        shop.setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Shop_Add_Data::class.java)
            startActivity(intent)
        })


    }
}