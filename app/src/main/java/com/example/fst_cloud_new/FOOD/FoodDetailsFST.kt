package com.example.fst_cloud_new.FOOD

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.Compare.ShoeCompareAble
import com.example.fst_cloud_new.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class FoodDetailsFST : AppCompatActivity() {

    lateinit var ratingBar: RatingBar
    lateinit var detailname : TextView
    lateinit var detaildescription : TextView
    lateinit var detailimage : ImageView
    var root_Node: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    lateinit var mAuth: FirebaseAuth
    lateinit var rating_review_list : ArrayList<food_detail_model>
    lateinit var review : EditText
    lateinit var rating_recycle : RecyclerView
    lateinit var adapter: food_detail_ratingRecycle_adapter


    //shared preference variable username
    var userName : String = ""
    var restaurantName : String = ""
    var shopName : String = ""



    var image : String = ""

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details_fst)

        supportActionBar?.hide()

        rating_review_list = ArrayList()
        mAuth = FirebaseAuth.getInstance()
//        val back : ImageView = findViewById(R.id.back)
        detailimage = findViewById(R.id.detailimage)

        detailname = findViewById(R.id.detailname)
        detaildescription  = findViewById(R.id.detailDescription)
        ratingBar = findViewById(R.id.ratingBar)
        review = findViewById(R.id.ed_review)
        var btn_submit = findViewById<Button>(R.id.btn_submit)
//var btn_back = findViewById<ImageView>(R.id.back_food).setOnClickListener {
//
//    var intent = Intent(this,FoodMainPageFST::class.java)
//    startActivity(intent)
//}
        //Recycle View Setting
        rating_recycle = findViewById(R.id.recycle_retings)
        rating_recycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)


        val compare = findViewById<Button>(R.id.btn_compare).setOnClickListener {
            compare()
        }

//get shared preferences

        var userNamesharedP = getSharedPreferences("get_username_on_signUp", Context.MODE_APPEND)
         userName = userNamesharedP.getString("user_name","anonymous").toString()



        var shopNamesharedP = getSharedPreferences("shop_name", Context.MODE_APPEND)
         shopName = shopNamesharedP.getString("shop_name","anonymous").toString()


        Toast.makeText(this, "name = " + userName +" " + restaurantName +" "+ shopName, Toast.LENGTH_SHORT).show()





        //Getting data from previous activity
        val bundle = intent.extras
        val name= bundle?.getString("dish_name")
        val description= bundle?.getString("dish_description")
         image = bundle?.getString("dish_image").toString()

        detailname.text = name.toString()
        detaildescription.text = description.toString()
        Picasso.get().load(image).into(detailimage)


        btn_submit.setOnClickListener {addData()}

//        back.setOnClickListener{
//            intent = Intent(this, FoodMainPageFST::class.java)
//            Toast.makeText(this, "rating = " + ratingBar.rating, Toast.LENGTH_SHORT).show()
//            // startActivity(intent)
//        }

        getRating()


    }

    fun addData(){
        mAuth.currentUser
        //Real Time data base Initialization code
        root_Node = FirebaseDatabase.getInstance()
        reference = root_Node!!.getReference("DishRating")

        var dish_rating = ratingBar.rating.toString()
        var dish_review = review.text.toString()


        var model = food_detail_model(dish_rating,dish_review,userName)


        reference!!.child(detailname.text.toString()).child(userName).setValue(model)


    }

    fun getRating() {
        reference = FirebaseDatabase.getInstance().getReference()
        val query : Query = reference!!.child("DishRating").child(detailname.text.toString())

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for (usersnapshot in snapshot.children){
                        var name = usersnapshot.child("dish_review").getValue().toString()
                        //Toast.makeText(this@FoodDetailsFST, "name = " + name, Toast.LENGTH_SHORT).show()
                        val rating_review = usersnapshot.getValue(food_detail_model::class.java)
                        if (rating_review != null) {
                            rating_review_list.add(rating_review)
                        }

                    }


                    adapter = food_detail_ratingRecycle_adapter(this@FoodDetailsFST,rating_review_list)
                    rating_recycle.adapter = adapter
                    rating_recycle.addItemDecoration(
                        DividerItemDecoration(
                            this@FoodDetailsFST,
                            LinearLayoutManager.HORIZONTAL
                        )
                    )



                  //  Toast.makeText(this@FoodDetailsFST, "rating = " + rating_review_list[0].dish_rating, Toast.LENGTH_SHORT).show()
                }

                else
                {
                    Toast.makeText(this@FoodDetailsFST, "Snapshot does not exist", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@FoodDetailsFST, "Something went wrong", Toast.LENGTH_SHORT).show()

            }
        })


    }

    fun compare()
    {
        val intent  = Intent(this, ShoeCompareAble::class.java)

//        intent.putExtra("name",detailname.text.toString())
//        intent.putExtra("description",detaildescription.text.toString())
//        intent.putExtra("image",image)

        startActivity(intent)

    }
}



