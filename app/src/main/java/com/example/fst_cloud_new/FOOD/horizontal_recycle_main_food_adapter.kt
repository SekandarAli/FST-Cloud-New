package com.example.fst_cloud_new.FOOD

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R

class horizontal_recycle_main_food_adapter (var items : ArrayList<horizontal_recycle_main_food_model>, context : Context) : RecyclerView.Adapter<horizontal_recycle_main_food_adapter.ViewHolder>() {


    val context = context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val v = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_recycle_mainpage, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = items[position].name
        holder.itemImage.setImageResource(items[position].image)

        holder.itemView.setOnClickListener(View.OnClickListener {
            var intent = Intent(context, FoodMainPageFST::class.java)
            context.startActivity(intent)
        })

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemName: TextView

        init {
            itemImage = itemView.findViewById(R.id.image)
            itemName = itemView.findViewById(R.id.name)

//            itemView.setOnClickListener(View.OnClickListener {
//                var intent = Intent(context,FoodMainPageFST::class.java)
//                ContextCompat.startActivity(context, intent, null)
//            })

        }



    }
}








