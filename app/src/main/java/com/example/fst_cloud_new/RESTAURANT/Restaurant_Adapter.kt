package com.example.fst_cloud_new.RESTAURANT

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

        //onclick

        holder.itemView.setOnClickListener(View.OnClickListener {
            var intent = Intent(context, FoodMainPageFST::class.java)
           // Toast.makeText(context, "" + currentItem.resturant_name, Toast.LENGTH_SHORT).show()
            intent.putExtra("restaurant_name",currentItem.resturant_name)
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



