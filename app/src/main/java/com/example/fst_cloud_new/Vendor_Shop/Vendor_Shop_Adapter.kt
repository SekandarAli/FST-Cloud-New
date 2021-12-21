package com.example.fst_cloud_new.Vendor_Shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.Vendor_Resturant.Vendor_Resturant_Model
import com.squareup.picasso.Picasso
import es.dmoral.toasty.Toasty

class Vendor_Shop_Adapter(var context : Context, var shop_list : ArrayList<Vendor_Shop_Model>)
    : RecyclerView.Adapter<Vendor_Shop_Adapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_vendor_resturant_recyclerview,
            parent,false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        var currentItem = shop_list[position]
        holder.shop_name.setText(currentItem.shop_name)
        holder.shop_location.setText(currentItem.shop_location)
        holder.shop_description.setText(currentItem.shop_description)

        if(currentItem.shop_image == null)
        {
            Toasty.error(context, "Image cannot be null", Toasty.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(currentItem.shop_image).into(holder.shop_image)
        }

        //Picasso.get().load(currentItem.resturant_image).into(holder.resturant_image)


    }

    override fun getItemCount(): Int {
        return shop_list.size
    }
    class MyViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView){

        var shop_name = itemView.findViewById<TextView>(R.id.resturant_name)
        var shop_location = itemView.findViewById<TextView>(R.id.resturant_location)
        var shop_description = itemView.findViewById<TextView>(R.id.resturant_description)
        var shop_image = itemView.findViewById<ImageView>(R.id.resturant_image)


    }

}
