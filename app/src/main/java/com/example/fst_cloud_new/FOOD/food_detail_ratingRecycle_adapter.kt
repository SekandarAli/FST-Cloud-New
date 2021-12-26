package com.example.fst_cloud_new.FOOD

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R

class food_detail_ratingRecycle_adapter(var context : Context, var review_List : ArrayList<food_detail_model>) : RecyclerView.Adapter<food_detail_ratingRecycle_adapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
      var view : View = LayoutInflater.from(context).inflate(R.layout.food_detail_recycleadapter_itemlayout,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       var currentItem = review_List[position]
        holder.rating.text  = currentItem.dish_rating
        holder.review.text  = currentItem.dish_review
        holder.user_name.text = currentItem.userName

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