package com.example.fst_cloud_new.SHOP

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.FOOD.FoodDetailsFST
import com.example.fst_cloud_new.FOOD.FoodMainPageFST
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.RESTAURANT.Restaurant_Model
import com.squareup.picasso.Picasso

class Shop_Adapter (var items : ArrayList<Shop_Model>, context
: Context) : RecyclerView.Adapter<Shop_Adapter.ViewHolder>() {


    val context = context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val v = LayoutInflater.from(parent.context).inflate(R.layout.vertical_recycle_mainpage, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var currentItem = items[position]
        holder.itemName.setText(currentItem.shop_name)
        holder.itemDescription.setText(currentItem.shop_description)
        holder.itemRating.setText(currentItem.shop_location)
        Picasso.get().load(currentItem.shop_image).into(holder.itemImage)

//onclick

        holder.itemView.setOnClickListener(View.OnClickListener {
            var intent = Intent(context, ShopMainPageFST::class.java)
            // Toast.makeText(context, "" + currentItem.resturant_name, Toast.LENGTH_SHORT).show()
            intent.putExtra("shop_name",currentItem.shop_name)
            startActivity(context,intent,null)
        })

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemName: TextView
        var itemDescription: TextView
        var itemRating: TextView



        init {
            itemImage = itemView.findViewById(R.id.image)
            itemName = itemView.findViewById(R.id.name)
            itemDescription = itemView.findViewById(R.id.description)
            itemRating = itemView.findViewById(R.id.rating)



        }



    }


}



