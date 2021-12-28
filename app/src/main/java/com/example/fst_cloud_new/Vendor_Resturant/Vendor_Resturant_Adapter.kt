package com.example.fst_cloud_new.Vendor_Resturant

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

class Vendor_Resturant_Adapter(var context : Context, var dish_list : ArrayList<Vendor_Resturant_Model>)
        : RecyclerView.Adapter<Vendor_Resturant_Adapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_vendor_resturant_recyclerview,
            parent,false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        var currentItem = dish_list[position]
        holder.resturant_name.setText(currentItem.resturant_name)
        holder.resturant_location.setText(currentItem.resturant_location)
        holder.resturant_description.setText(currentItem.resturant_description)

        if(currentItem.resturant_image == null)
        {
            Toast.makeText(context, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(currentItem.resturant_image).into(holder.resturant_image)
        }




    }

    override fun getItemCount(): Int {
        return dish_list.size
    }
    class MyViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView){

        var resturant_name = itemView.findViewById<TextView>(R.id.resturant_name)
        var resturant_location = itemView.findViewById<TextView>(R.id.resturant_location)
        var resturant_description = itemView.findViewById<TextView>(R.id.resturant_description)
        var resturant_image = itemView.findViewById<ImageView>(R.id.resturant_image)


    }

}
