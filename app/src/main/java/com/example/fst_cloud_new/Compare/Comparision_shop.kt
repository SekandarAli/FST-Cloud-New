package com.example.fst_cloud_new.Compare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.fst_cloud_new.R
import com.squareup.picasso.Picasso

class Comparision_shop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparision_shop)


//Dish 1 Views
//        var tv_dish1_name = findViewById<TextView>(R.id.dish1_name)
//        var tv_dish1_price = findViewById<TextView>(R.id.dish1_price)
//        var tv_dish1_description = findViewById<TextView>(R.id.dish1_description)
//        var tv_dish1_image = findViewById<ImageView>(R.id.dish1_image)

        //Dish 2 Views

        var tv_dish2_name = findViewById<TextView>(R.id.dish2_name)
        var tv_dish2_price = findViewById<TextView>(R.id.dish2_price)
        var tv_dish2_description = findViewById<TextView>(R.id.dish2_description)
        var img_dish2_image = findViewById<ImageView>(R.id.dish2_image)





        var intent = intent
        tv_dish2_name.text = intent.getStringExtra("name")
        Toast.makeText(this, "name =" + intent.getStringExtra("name"), Toast.LENGTH_SHORT).show()
        tv_dish2_description.text = intent.getStringExtra("description")
        tv_dish2_price.text = intent.getStringExtra("price")
        var image = intent.getStringExtra("image")
        if(image == null)
        {

            Toast.makeText(this, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(image).into(img_dish2_image)
        }



    }


    }
