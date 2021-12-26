package com.example.fstsignin.Compare

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.Compare.Comparision_Acivity
import com.example.fst_cloud_new.R

import com.squareup.picasso.Picasso

class Compare_Dish_Adapter(var context : Context, var dish_list : ArrayList<Compare_Model>)
    : RecyclerView.Adapter<Compare_Dish_Adapter.MyViewHolder>(){






    class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView){

        var dish_name = itemView.findViewById<TextView>(R.id.vertical_name)
        var dish_price = itemView.findViewById<TextView>(R.id.vertical_price)
        var dish_description = itemView.findViewById<TextView>(R.id.vertical_description)
        var dish_image = itemView.findViewById<ImageView>(R.id.vertical_image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Compare_Dish_Adapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.food_main_vertical,
            parent,false)


        return Compare_Dish_Adapter.MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return dish_list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var currentItem = dish_list[position]
        holder.dish_name.setText(currentItem.dish_name)
        holder.dish_price.setText(currentItem.dish_price)
        holder.dish_description.setText(currentItem.dish_description)


        if(currentItem.dish_image == null)
        {

            Toast.makeText(context, "Image cannot be null", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Picasso.get().load(currentItem.dish_image).into(holder.dish_image)
        }

        holder.itemView.setOnClickListener {

            var intent = Intent(context, Comparision_Acivity::class.java)
            intent.putExtra("name",dish_list[position].dish_name)
            intent.putExtra("description",dish_list[position].dish_description)
            intent.putExtra("price",dish_list[position].dish_price)
            intent.putExtra("image",dish_list[position].dish_image)


            context.startActivity(intent)

        }
    }

}