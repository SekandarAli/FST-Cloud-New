package com.example.fst_cloud_new.SHOP

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
import com.example.fst_cloud_new.FOOD.horizontal_recycle_main_food_adapter
import com.example.fst_cloud_new.FOOD.horizontal_recycle_main_food_model
import com.example.fst_cloud_new.FOOD.vertical_recycle_main_food_adapter
import com.example.fst_cloud_new.FOOD.vertical_recycle_main_food_model

class ShopFragment (context: Context): Fragment() {

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
            R.layout.shop_fragment_fst,
            container,false)

        val items = ArrayList<horizontal_recycle_main_food_model>()


        items.add(
            horizontal_recycle_main_food_model(
                "Khadi",
                R.drawable.s1
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "Nishat Lilan",
                R.drawable.s2
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "Kids Store",
                R.drawable.s3
            )
        )

        items.add(
            horizontal_recycle_main_food_model(
                "Cantt Store",
                R.drawable.s4
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "Hush puppies",
                R.drawable.s5
            )
        )
        items.add(
            horizontal_recycle_main_food_model(
                "Stone Harbor",
                R.drawable.s6
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
                "Khadi", "located in bazar", "3 min | 4.2 stars",
                R.drawable.s7
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Stone Harbor", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.s8
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Outfitters", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.s9
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Engine jeans", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.s10
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Nishat Lilan", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.s1
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Khadi", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.s2
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Guldberg", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.s3
            )
        )

        vitems.add(
            vertical_recycle_main_food_model(
                "Kids Store", "North Food special plate", "3 min | 4.2 stars",
                R.drawable.s4
            )
        )


        val vrecycleView: RecyclerView = v.findViewById(R.id.verticalrecycleView)
        vrecycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.VERTICAL, false)

        vadapter = vertical_recycle_main_food_adapter(vitems, food_context)
        vrecycleView.adapter = vadapter

        val DividerItemDecoration = DividerItemDecoration(food_context, DividerItemDecoration.VERTICAL)
        vrecycleView.addItemDecoration(DividerItemDecoration)


        return v
    }

}


