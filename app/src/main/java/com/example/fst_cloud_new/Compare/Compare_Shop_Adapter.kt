package com.example.fst_cloud_new.Compare

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.squareup.picasso.Picasso

class Compare_Shop_Adapter (var context : Context, var shop_list : ArrayList<compare_model_shop>)
    : RecyclerView.Adapter<Compare_Shop_Adapter.MyViewHolder>(){






    class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView){

        var dish_name = itemView.findViewById<TextView>(R.id.vertical_name)
        var dish_price = itemView.findViewById<TextView>(R.id.vertical_price)
        var dish_description = itemView.findViewById<TextView>(R.id.vertical_description)
        var dish_image = itemView.findViewById<ImageView>(R.id.vertical_image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Compare_Shop_Adapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.food_main_vertical,
            parent,false)


        return Compare_Shop_Adapter.MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return shop_list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var currentItem = shop_list[position]
        holder.dish_name.setText(currentItem.shop_main_name)
        holder.dish_price.setText(currentItem.shop_main_price)
        holder.dish_description.setText(currentItem.shop_main_description)

      //  Toast.makeText(context, "image = " + currentItem.shop_main_image.toString(), Toast.LENGTH_SHORT).show()


        if(currentItem.shop_main_image.isEmpty())
        {

            Toast.makeText(context, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(currentItem.shop_main_image).into(holder.dish_image)
        }

        holder.itemView.setOnClickListener {

            var intent = Intent(context, Comparision_shop::class.java)
            intent.putExtra("name",shop_list[position].shop_main_name)
            intent.putExtra("description",shop_list[position].shop_main_description)
            intent.putExtra("price",shop_list[position].shop_main_price)
            intent.putExtra("image",shop_list[position].shop_main_image)


            context.startActivity(intent)

        }
    }

}