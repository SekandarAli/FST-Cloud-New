package com.example.fst_cloud_new.FOOD

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R

class FoodFragment (context: Context): Fragment() {

    var food_context = context
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var foodAdapter: RecyclerView.Adapter<horizontal_recycle_main_food_adapter.ViewHolder>? = null
    private var vlayoutManager: RecyclerView.LayoutManager? = null
    private var vadapter: RecyclerView.Adapter<vertical_recycle_main_food_adapter.ViewHolder>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    var v : View = LayoutInflater.from(food_context).inflate(
        R.layout.food_fragment_fst,
        container,false)

    val items = ArrayList<horizontal_recycle_main_food_model>()


        items.add(
            horizontal_recycle_main_food_model(
                "Saif Bakers",
                R.drawable.f10
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "Food Plannet",
                R.drawable.f9
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "De'Mininster cafe",
                R.drawable.f8
            )
        )

        items.add(
            horizontal_recycle_main_food_model(
                "Saif Bakers",
                R.drawable.f7
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "Food Plannet",
                R.drawable.f6
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "De'Mininster cafe",
                R.drawable.r5
            )
        )


        // layoutManager = LinearLayoutManager(this)

        val recycleView: RecyclerView = v.findViewById(R.id.horizontalrecycleView)
        recycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.HORIZONTAL, false)

        foodAdapter = horizontal_recycle_main_food_adapter(items, food_context)
        recycleView.adapter = foodAdapter










        val vitems = ArrayList<vertical_recycle_main_food_model>()

        vitems.add(
            vertical_recycle_main_food_model(
                "De'Mininster cafe", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.r6
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Coffity", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.r7
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Mir Bakers", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.r8
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Usmania Resturant", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.f10
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "De'Mininster cafe", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.f6
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Mir Bakers", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.r9
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Usmania Resturant", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.f7
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "De'Mininster cafe", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.f8
            )
        )


        val vrecycleView: RecyclerView = v.findViewById(R.id.restaurant_vertical_recycleView)
        vrecycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.VERTICAL, false)

        vadapter = vertical_recycle_main_food_adapter(vitems, food_context)
        vrecycleView.adapter = vadapter

        val DividerItemDecoration = DividerItemDecoration(food_context, DividerItemDecoration.VERTICAL)
        vrecycleView.addItemDecoration(DividerItemDecoration)


    return v
    }

}


