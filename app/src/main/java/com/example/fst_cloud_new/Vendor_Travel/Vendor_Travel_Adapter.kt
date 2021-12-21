package com.example.fst_cloud_new.Vendor_Travel

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

class Vendor_Travel_Adapter (var context : Context, var dish_list : ArrayList<Vendor_Travel_Model>)
    : RecyclerView.Adapter<Vendor_Travel_Adapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_vendor_resturant_recyclerview,
            parent,false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        var currentItem = dish_list[position]
        holder.travel_name.setText(currentItem.travel_name)
        holder.travel_location.setText(currentItem.travel_location)
        holder.travel_description.setText(currentItem.travel_description)

        if(currentItem.travel_image == null)
        {
            Toast.makeText(context, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(currentItem.travel_image).into(holder.travel_image)
        }

        //Picasso.get().load(currentItem.travel_image).into(holder.travel_image)


    }

    override fun getItemCount(): Int {
        return dish_list.size
    }
    class MyViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView){

        var travel_name = itemView.findViewById<TextView>(R.id.resturant_name)
        var travel_location = itemView.findViewById<TextView>(R.id.resturant_location)
        var travel_description = itemView.findViewById<TextView>(R.id.resturant_description)
        var travel_image = itemView.findViewById<ImageView>(R.id.resturant_image)


    }

}
