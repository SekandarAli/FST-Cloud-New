package com.example.fst_cloud_new.RESTAURANT

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.FOOD.FoodMainPageFST
import com.example.fst_cloud_new.R
import com.squareup.picasso.Picasso


class Restaurant_Adapter(var items : ArrayList<Restaurant_Model>, context
: Context) : RecyclerView.Adapter<Restaurant_Adapter.ViewHolder>() {


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
        holder.itemName.setText(currentItem.resturant_name)
        holder.itemDescription.setText(currentItem.resturant_description)
        holder.itemRating.setText(currentItem.resturant_location)
        Picasso.get().load(currentItem.resturant_image).into(holder.itemImage)

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
                var intent = Intent(context, FoodMainPageFST::class.java)
                startActivity(context,intent,null)
            })

        }



    }


}



