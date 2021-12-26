package com.example.fst_cloud_new.SHOP

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.FOOD.food_detail_model
import com.example.fst_cloud_new.FOOD.food_detail_ratingRecycle_adapter
import com.example.fst_cloud_new.R

class shop_detail_ratingRecycle_adapter (var context : Context, var review_List : ArrayList<shop_detail_model>) : RecyclerView.Adapter<shop_detail_ratingRecycle_adapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view : View = LayoutInflater.from(context).inflate(R.layout.food_detail_recycleadapter_itemlayout,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var currentItem = review_List[position]
        holder.rating.text  = currentItem.shop_rating
        holder.review.text  = currentItem.shop_review

    }

    override fun getItemCount(): Int {
        return review_List.size
    }


    class viewHolder (var itemView : View): RecyclerView.ViewHolder(itemView){

        var user_name = itemView.findViewById<TextView>(R.id.tv_username)
        var rating = itemView.findViewById<TextView>(R.id.tv_rating)
        var review = itemView.findViewById<TextView>(R.id.tv_userReview)


    }
}