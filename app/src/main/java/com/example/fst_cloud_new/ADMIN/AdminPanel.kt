package com.example.fst_cloud_new.ADMIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Show_Data
import com.example.fst_cloud_new.Vendor_Resturant.Vendor_Restaurant_Show_Data
import com.example.fst_cloud_new.Vendor_Shop.Vendor_Shop_Show_Data
import com.example.fst_cloud_new.Vendor_Shop_Category.Vendor_Shop_Category_Show_Data
import com.example.fst_cloud_new.Vendor_Travel.Vendor_Travel_Add_Data
import com.example.fst_cloud_new.Vendor_Travel.Vendor_Travel_Show_Data

class AdminPanel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)


        supportActionBar?.hide()

        val admin_show_dish : Button = findViewById(R.id.admin_show_dish)
        val admin_show_resaurant : Button = findViewById(R.id.admin_show_restaurant)
        val admin_show_home : Button = findViewById(R.id.admin_show_home)
        val admin_show_shop : Button = findViewById(R.id.admin_show_shop)
        val admin_show_shop_item : Button = findViewById(R.id.admin_show_shop_items)
        val admin_add_travel : Button = findViewById(R.id.admin_add_travel)
        val admin_show_travel : Button = findViewById(R.id.admin_show_travel)


        admin_show_shop_item .setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Shop_Category_Show_Data::class.java)
            startActivity(intent)
        })

        admin_show_travel.setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Travel_Show_Data::class.java)
            startActivity(intent)
        })

        admin_show_dish.setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Dish_Show_Data::class.java)
            startActivity(intent)
        })

        admin_show_shop.setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Shop_Show_Data::class.java)
            startActivity(intent)
        })

        admin_add_travel.setOnClickListener(View.OnClickListener {

            intent = Intent(this,Vendor_Travel_Add_Data::class.java)
            startActivity(intent)
        })


        admin_show_resaurant.setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Restaurant_Show_Data::class.java)
            startActivity(intent)
        })


        admin_show_home.setOnClickListener(View.OnClickListener {

            intent = Intent(this, HOMEPAGE::class.java)
            startActivity(intent)
        })


    }
}