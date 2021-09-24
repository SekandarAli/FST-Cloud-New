package com.example.fst_cloud_new.Dish_Main_Page

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Model
import com.squareup.picasso.Picasso

class Dish_Main_page_Adapter (var context : Context, var dish_list : ArrayList<Vendor_Dish_Model>)
    : RecyclerView.Adapter<Dish_Main_page_Adapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.food_main_vertical,
            parent,false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        var currentItem = dish_list[position]
        holder.dish_name.setText(currentItem.dish_name)
        holder.dish_price.setText(currentItem.dish_price)
        holder.dish_description.setText(currentItem.dish_description)

        if(currentItem.dish_image == null)
        {

            Toast.makeText(context, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(currentItem.dish_image).into(holder.dish_image)
        }

//        holder.itemView.setOnClickListener(View.OnClickListener {
//
//            var intent = Intent(context, FoodDetailsFST::class.java)
//            intent.putExtra("name",dish_list[position].dish_name)
//            //intent.putExtra("description",descriptionn)
//            intent.putExtra("image",dish_list[position].dish_image)
//
//
//            context.startActivity(intent)
//
//        })


    }

    override fun getItemCount(): Int {
        return dish_list.size
    }
    class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView){

        var dish_name = itemView.findViewById<TextView>(R.id.vertical_name)
        var dish_price = itemView.findViewById<TextView>(R.id.vertical_price)
        var dish_description = itemView.findViewById<TextView>(R.id.vertical_description)
        var dish_image = itemView.findViewById<ImageView>(R.id.vertical_image)


    }

}



