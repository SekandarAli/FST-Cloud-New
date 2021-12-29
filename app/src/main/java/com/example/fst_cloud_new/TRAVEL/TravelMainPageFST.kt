package com.example.fst_cloud_new.TRAVEL

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.MAPS.Map_User
import com.example.fst_cloud_new.Main_Page_Adapter.Main_horizontal_card_adapter
import com.example.fst_cloud_new.Main_Page_Adapter.Main_horizontal_list_adapter
import com.example.fst_cloud_new.Main_Page_Adapter.Main_vertical_adapter
import com.example.fst_cloud_new.Main_Page_Model.Main_horizontal_card_model
import com.example.fst_cloud_new.Main_Page_Model.Main_horizontal_list_model
import com.example.fst_cloud_new.Main_Page_Model.Main_vertical_model
import com.example.fst_cloud_new.SEARCH.Searching_User

class TravelMainPageFST : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<Main_horizontal_list_adapter.ViewHolder>? = null
    private var vlayoutManager: RecyclerView.LayoutManager? = null
    private var vadapter: RecyclerView.Adapter<new_travel_adapter_.ViewHolder>? = null
    private var hadapter: RecyclerView.Adapter<Main_horizontal_card_adapter.ViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_main_page_fst)

        supportActionBar?.hide()

        val back : ImageView = findViewById(R.id.back)
      //  val location : ImageView = findViewById(R.id.location)
        val travel_search : ImageView = findViewById(R.id.travel_search)

        back.setOnClickListener{
            intent = Intent(this, HOMEPAGE::class.java)
            startActivity(intent)
        }

//
//        location.setOnClickListener{
//            intent = Intent(this, Map_User::class.java)
//            startActivity(intent)
//        }

        travel_search.setOnClickListener{
            intent = Intent(this, Searching_User::class.java)
            startActivity(intent)
        }


        val items = ArrayList<Main_horizontal_list_model>()


        items.add(
            Main_horizontal_list_model(
                "Murree",
                R.drawable.t1
            )
        )
        items.add(
            Main_horizontal_list_model(
                "nathiagali",
                R.drawable.t2
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Ayubia",
                R.drawable.t3
            )
        )

        items.add(
            Main_horizontal_list_model(
                "Shimla Hills",
                R.drawable.t4
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Ilyasi",
                R.drawable.t5
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Nathia",
                R.drawable.t6
            )
        )


        items.add(
            Main_horizontal_list_model(
                "Karla",
                R.drawable.t7
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Waterfall",
                R.drawable.t8
            )
        )


        // layoutManager = LinearLayoutManager(this)

        val recycleView: RecyclerView = findViewById(R.id.foodhorizontallist)
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = Main_horizontal_list_adapter(items, this)

        recycleView.adapter = adapter



        val hitems = ArrayList<Main_horizontal_card_model>()


        hitems.add(
            Main_horizontal_card_model(
                "Murree",
                R.drawable.t9
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Ayubia",
                R.drawable.t8
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Harnoi",
                R.drawable.t7
            )
        )

        hitems.add(
            Main_horizontal_card_model(
                "Waterfall",
                R.drawable.t6
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Shimla",
                R.drawable.t5
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Mountains",
                R.drawable.t4
            )
        )


         layoutManager = LinearLayoutManager(this)

        val recycleViewcard: RecyclerView = findViewById(R.id.foodhorizontalcard)
        recycleViewcard.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        hadapter = Main_horizontal_card_adapter(hitems, this)

        recycleViewcard.adapter = hadapter

        val vitems = ArrayList<Main_vertical_model>()

        vitems.add(
            Main_vertical_model(
                "Nathiagali", "A special place to visit for snow", "3 hours | 4.5 stars",
                R.drawable.t6
            )
        )

        vitems.add(
            Main_vertical_model(
                "Waterfall", "A place to visit for water people", "38 min | 4.2 stars",
                R.drawable.t8
            )
        )

        vitems.add(
            Main_vertical_model(
                "Hills", "A hilly area ", "21 min | 3.2 stars",
                R.drawable.t4
            )
        )

        vitems.add(
            Main_vertical_model(
                "Ayubia", "A special place to visit for snow", "4 hours | 4.9 stars",
                R.drawable.t2
            )
        )

        vitems.add(
            Main_vertical_model(
                "Karla", "Water place like umbrella waterfall", "57 mins | 3.9 stars",
                R.drawable.t7
            )
        )

        vitems.add(
            Main_vertical_model(
                "Murree", "A special place to visit for snow", "1 hours | 4.2 stars",
                R.drawable.t9
            )
        )



        val vrecycleView: RecyclerView = findViewById(R.id.verticalfood)
        vrecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        vadapter = new_travel_adapter_(vitems, this)
        vrecycleView.adapter = vadapter

        val DividerItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        vrecycleView.addItemDecoration(DividerItemDecoration)


    }


}