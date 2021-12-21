package com.example.fst_cloud_new.Vendor_Shop_Category

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


class Vendor_Shop_Category_Adapter  (var context : Context, var shop_main_list :
ArrayList<Vendor_Shop_Category_Model>)
    : RecyclerView.Adapter<Vendor_Shop_Category_Adapter.MyViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_vendor_shop_recyclerview,
            parent,false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        var currentItem = shop_main_list[position]
        holder.shop_main_name.setText(currentItem.shop_main_name)
        holder.shop_main_price.setText(currentItem.shop_main_price)
        holder.shop_main_description.setText(currentItem.shop_main_description)

        if(currentItem.shop_main_image == null)
        {
            Toast.makeText(context, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(currentItem.shop_main_image).into(holder.shop_main_image)
        }

        //Picasso.get().load(currentItem.shop_main_image).into(holder.shop_main_image)


    }

    override fun getItemCount(): Int {
        return shop_main_list.size
    }
    class MyViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView){

        var shop_main_name = itemView.findViewById<TextView>(R.id.dish_name)
        var shop_main_price = itemView.findViewById<TextView>(R.id.dish_price)
        var shop_main_description = itemView.findViewById<TextView>(R.id.dish_description)
        var shop_main_image = itemView.findViewById<ImageView>(R.id.dish_image)


    }

}



