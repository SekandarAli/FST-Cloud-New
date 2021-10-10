package com.example.fst_cloud_new.TRAVEL

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
import com.example.fst_cloud_new.FOOD.horizontal_recycle_fragment_food_adapter
import com.example.fst_cloud_new.FOOD.horizontal_recycle_fragment_food_model
import com.example.fst_cloud_new.SHOP.vertical_recycle_fragment_shop_model

class TravelFragment (context: Context): Fragment() {

    var food_context = context
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var foodAdapter: RecyclerView.Adapter<horizontal_recycle_main_travel_adapter.ViewHolder>? = null
    private var vlayoutManager: RecyclerView.LayoutManager? = null
    private var vadapter: RecyclerView.Adapter<vertical_recycle_main_travel_adapter.ViewHolder>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v : View = LayoutInflater.from(food_context).inflate(
            R.layout.travel_fragment_fst,
            container,false)

        val items = ArrayList<horizontal_recycle_fragment_food_model>()


        items.add(
            horizontal_recycle_fragment_food_model(
                "Murree",
                R.drawable.t1
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "Nathiagali",
                R.drawable.t2
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "Ilyasi Mosque",
                R.drawable.t3
            )
        )

        items.add(
            horizontal_recycle_fragment_food_model(
                "Harno",
                R.drawable.t4
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "shimla Pahari",
                R.drawable.t5
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "Township",
                R.drawable.t6
            )
        )


        // layoutManager = LinearLayoutManager(this)

        val recycleView: RecyclerView = v.findViewById(R.id.horizontalrecycleViewtravel)
        recycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.HORIZONTAL, false)

        foodAdapter = horizontal_recycle_main_travel_adapter(items, food_context)
        recycleView.adapter = foodAdapter



        val vitems = ArrayList<vertical_recycle_main_travel_model>()

        vitems.add(
            vertical_recycle_main_travel_model(
                "Ilyasi Mosque", "located in road", "3 min | 4.2 stars",
                R.drawable.t7
            )
        )

        vitems.add(
            vertical_recycle_main_travel_model(
                "Township", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.t8
            )
        )

        vitems.add(
            vertical_recycle_main_travel_model(
                "Murree", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.t9
            )
        )

        vitems.add(
            vertical_recycle_main_travel_model(
                "Ayubia", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.t1
            )
        )

        vitems.add(
            vertical_recycle_main_travel_model(
                "Township", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.t2
            )
        )

        vitems.add(
            vertical_recycle_main_travel_model(
                "Shimla Mountains", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.t3
            )
        )

        vitems.add(
            vertical_recycle_main_travel_model(
                "Harnoi", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.t4
            )
        )

        vitems.add(
            vertical_recycle_main_travel_model(
                "Nathiagali", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.t6
            )
        )


        val vrecycleView: RecyclerView = v.findViewById(R.id.verticalrecycleViewtravel)
        vrecycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.VERTICAL, false)

        vadapter = vertical_recycle_main_travel_adapter(vitems, food_context)
        vrecycleView.adapter = vadapter

        val DividerItemDecoration = DividerItemDecoration(food_context, DividerItemDecoration.VERTICAL)
        vrecycleView.addItemDecoration(DividerItemDecoration)


        return v
    }

}


