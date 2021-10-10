package com.example.fst_cloud_new.TRAVEL

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.SHOP.vertical_recycle_fragment_shop_model
import com.example.fst_cloud_new.R

class vertical_recycle_main_travel_adapter (var items : ArrayList<vertical_recycle_main_travel_model>, context
: Context
) : RecyclerView.Adapter<vertical_recycle_main_travel_adapter.ViewHolder>() {


    val context = context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val v = LayoutInflater.from(parent.context).inflate(R.layout.vertical_recycle_mainpage, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = items[position].resturant_name
        holder.itemDescription.text = items[position].resturant_description
        holder.itemRating.text = items[position].resturant_location
        holder.itemImage.setImageResource(items[position].resturant_image)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemName: TextView
        var itemDescription: TextView
        var itemRating: TextView

//        itemView.setOnClickListener(View.OnClickListener {
//            intent = Intent(context,FoodMainPageFST::class.java)
//            startActivity(intent)
//        })

        init {
            itemImage = itemView.findViewById(R.id.image)
            itemName = itemView.findViewById(R.id.name)
            itemDescription = itemView.findViewById(R.id.description)
            itemRating = itemView.findViewById(R.id.rating)

            itemView.setOnClickListener(View.OnClickListener {
                var intent = Intent(context, TravelMainPageFST::class.java)
                ContextCompat.startActivity(context, intent, null)
            })

        }



    }


}