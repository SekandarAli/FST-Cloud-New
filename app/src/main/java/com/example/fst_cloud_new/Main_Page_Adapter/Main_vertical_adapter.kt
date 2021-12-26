package com.example.fst_cloud_new.Main_Page_Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.FOOD.FoodDetailsFST
import com.example.fst_cloud_new.Main_Page_Model.Main_vertical_model
import com.example.fst_cloud_new.SHOP.ShopDetailsFST

class Main_vertical_adapter (var items : ArrayList<Main_vertical_model>, context
                : Context) : RecyclerView.Adapter<Main_vertical_adapter.ViewHolder>() {


    val context = context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val v = LayoutInflater.from(parent.context).inflate(R.layout.food_main_vertical, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = items[position]
        holder.itemName.text = items[position].name
        holder.itemDescription.text = items[position].description
        holder.itemRating.text = items[position].rating
        holder.itemImage.setImageResource(items[position].image)


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemName: TextView
        var itemDescription: TextView
        var itemRating: TextView
        var view = itemView

        init {
            itemImage = itemView.findViewById(R.id.vertical_image)
            itemName = itemView.findViewById(R.id.vertical_name)
            itemDescription = itemView.findViewById(R.id.vertical_description)
            itemRating = itemView.findViewById(R.id.vertical_price)

            itemView.setOnClickListener(View.OnClickListener {
                var intent = Intent(context, FoodDetailsFST::class.java)
                ContextCompat.startActivity(context, intent, null)
            })


        }



    }




}