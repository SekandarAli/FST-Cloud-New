package com.example.fst_cloud_new.VENDOR_Shop_AND_Restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Add_Data
import com.example.fst_cloud_new.Vendor_Shop_Category.Vendor_Shop_Category_Add_Data

class Vendor_Shop_Category_Main_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_shop_category_main_page)

        val vendor_add_shop_category : Button = findViewById(R.id.vendor_add_shop_category)


        vendor_add_shop_category.setOnClickListener(View.OnClickListener {

            intent = Intent(this, Vendor_Shop_Category_Add_Data ::class.java)
            startActivity(intent)
        })



    }

    fun addmore(view: View) {

        Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show()

    }
}