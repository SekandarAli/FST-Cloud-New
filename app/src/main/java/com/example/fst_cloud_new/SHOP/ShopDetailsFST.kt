package com.example.fst_cloud_new.SHOP

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.FOOD.FoodMainPageFST
import com.example.fst_cloud_new.FOOD.food_detail_model
import com.example.fst_cloud_new.FOOD.food_detail_ratingRecycle_adapter
import com.example.fst_cloud_new.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class ShopDetailsFST : AppCompatActivity() {

    lateinit var ratingBar: RatingBar
    lateinit var detailname : TextView
    var root_Node: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    lateinit var mAuth: FirebaseAuth
    lateinit var rating_review_list : ArrayList<shop_detail_model>
    lateinit var review : EditText
    lateinit var rating_recycle : RecyclerView
    lateinit var adapter: shop_detail_ratingRecycle_adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details_fst)


        supportActionBar?.hide()
        rating_review_list = ArrayList()
        mAuth = FirebaseAuth.getInstance()
        val back : ImageView = findViewById(R.id.back)
        val detailimage : ImageView = findViewById(R.id.detailimage)
        detailname = findViewById(R.id.detailname)
        val detaildescription : TextView = findViewById(R.id.detailDescription)
        ratingBar = findViewById(R.id.ratingBar)
        review = findViewById(R.id.ed_review)
        var btn_submit = findViewById<Button>(R.id.btn_submit)

        //Recycle View Setting
        rating_recycle = findViewById(R.id.recycle_retings)
        rating_recycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

//Getting data from previous activity
        val bundle = intent.extras
        val name= bundle?.getString("dish_name")
        val description= bundle?.getString("dish_description")
        val image = bundle?.getString("dish_image")

        detailname.text = name.toString()
        detaildescription.text = description.toString()
        Picasso.get().load(image).into(detailimage)


        btn_submit.setOnClickListener {addData()}
        getRating()

        back.setOnClickListener{
            intent = Intent(this, FoodMainPageFST::class.java)
            Toast.makeText(this, "rating = " + ratingBar.rating, Toast.LENGTH_SHORT).show()
            // startActivity(intent)
        }
    }

    fun addData(){
        mAuth.currentUser
        //Real Time data base Initialization code
        root_Node = FirebaseDatabase.getInstance()
        reference = root_Node!!.getReference("ShopRating")

        var shop_rating = ratingBar.rating.toString()
        var shop_review = review.text.toString()
        var userName = "Assad"

        var model = shop_detail_model(shop_rating,shop_review,userName)


        reference!!.child(detailname.text.toString()).child(userName).setValue(model)


    }

    fun getRating() {
        reference = FirebaseDatabase.getInstance().getReference()
        val query : Query = reference!!.child("ShopRating").child(detailname.text.toString())

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for (usersnapshot in snapshot.children){
                        var name = usersnapshot.child("shop_review").getValue().toString()
                        //Toast.makeText(this@FoodDetailsFST, "name = " + name, Toast.LENGTH_SHORT).show()
                        val rating_review = usersnapshot.getValue(shop_detail_model::class.java)
                        if (rating_review != null) {
                            rating_review_list.add(rating_review)
                        }

                    }



                    Toast.makeText(this@ShopDetailsFST, "rating = " + rating_review_list[0].shop_rating, Toast.LENGTH_SHORT).show()
                }

                else
                {
                    Toast.makeText(this@ShopDetailsFST, "Snapshot does not exist", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ShopDetailsFST, "Something went wrong", Toast.LENGTH_SHORT).show()

            }
        })

        adapter = shop_detail_ratingRecycle_adapter(this,rating_review_list)
        rating_recycle.adapter = adapter
    }
}