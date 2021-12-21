package com.example.fst_cloud_new.TRAVEL

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.RESTAURANT.Restaurant_Model
import com.squareup.picasso.Picasso

class Travel_Adapter  (var items : ArrayList<Travel_Model>, context
: Context) : RecyclerView.Adapter<Travel_Adapter.ViewHolder>() {


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
        holder.itemName.setText(currentItem.travel_name)
        holder.itemDescription.setText(currentItem.travel_description)
        holder.itemRating.setText(currentItem.travel_location)
        Picasso.get().load(currentItem.travel_image).into(holder.itemImage)

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
                startActivity(context,intent,null)
            })

        }



    }


}



