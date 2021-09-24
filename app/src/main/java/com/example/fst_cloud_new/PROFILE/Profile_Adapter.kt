package com.example.fst_cloud_new.PROFILE

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.squareup.picasso.Picasso

class Profile_Adapter (var context : Context, var dish_list : ArrayList<Profile_Model>)
    : RecyclerView.Adapter<Profile_Adapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.navigation_header,
            parent,false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        var currentItem = dish_list[position]
        holder.vendor_username.setText(currentItem.vendor_username)
        holder.vendor_email.setText(currentItem.vendor_email)

        if(currentItem.vendor_image == null)
        {

            Toast.makeText(context, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else {
            Picasso.get().load(currentItem.vendor_image).into(holder.vendor_image)


        }
    }

    override fun getItemCount(): Int {
        return dish_list.size
    }
    class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView){

        var vendor_username = itemView.findViewById<TextView>(R.id.textView)
        var vendor_email = itemView.findViewById<TextView>(R.id.textView2)
        var vendor_image = itemView.findViewById<ImageView>(R.id.img1)


    }

}


