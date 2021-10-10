package com.example.fst_cloud_new.Vendor_Dish

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.squareup.picasso.Picasso


class Vendor_Dish_Adapter (var context : Context, var dish_list : ArrayList<Vendor_Dish_Model>)
    : RecyclerView.Adapter<Vendor_Dish_Adapter.MyViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_vendor_dish_recyclerview,
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

     //Picasso.get().load(currentItem.dish_image).into(holder.dish_image)


    }

    override fun getItemCount(): Int {
        return dish_list.size
    }
    class MyViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView){

        var dish_name = itemView.findViewById<TextView>(R.id.dish_name)
        var dish_price = itemView.findViewById<TextView>(R.id.dish_price)
        var dish_description = itemView.findViewById<TextView>(R.id.dish_description)
        var dish_image = itemView.findViewById<ImageView>(R.id.dish_image)


    }

}



