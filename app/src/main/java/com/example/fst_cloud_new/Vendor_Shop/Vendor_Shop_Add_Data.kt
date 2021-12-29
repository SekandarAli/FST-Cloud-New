package com.example.fst_cloud_new.Vendor_Shop


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fst_cloud_new.MAPS.Map_Vendor
import com.example.fst_cloud_new.MAPS.Map_Vendor_shop
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SIGN_UP.FST_Vendor_Signup
import com.example.fst_cloud_new.Start_Pages.FSTRegisterPage
import com.example.fst_cloud_new.VENDOR_Shop_AND_Restaurant.Vendor_Dish_Main_Page
import com.example.fst_cloud_new.VENDOR_Shop_AND_Restaurant.Vendor_Shop_Category_Main_Page
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Add_Data
import com.example.fst_cloud_new.Vendor_Shop_Category.Vendor_Shop_Category_Add_Data
import com.example.fst_cloud_new.Verification.verification_model
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import es.dmoral.toasty.Toasty
import java.io.IOException
import java.util.*

class Vendor_Shop_Add_Data : AppCompatActivity() {  


    lateinit var next: Button


    var root_Node: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    lateinit var vendor_shop_imageView: ImageView
    lateinit var mAuth: FirebaseAuth
    lateinit var vendor_shop_name: EditText
    lateinit var vendor_shop_description: EditText
    lateinit var vendor_shop_location: EditText
    lateinit var vendor_shop_chooseImage: Button
    lateinit var vendor_add_shop_category_data: Button

    //Strings and constant

    var location: String = ""
    var PICK_IMAGE_REQUEST = 22

    //Database storage variables

    lateinit var db: FirebaseStorage
    lateinit var Storageref: StorageReference
    lateinit var filepath: Uri
    lateinit var add_data: Button
    lateinit var vendor_Location_data: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_shop_add_data)


        isUserVerified()

//        vendor_add_shop_category_data = findViewById(R.id.vendor_add_shop_category_data)
//
//        vendor_add_shop_category_data.setOnClickListener {
//
//            intent = Intent(this, Vendor_Shop_Category_Add_Data::class.java)
//            startActivity(intent)
//
//        }


        add_data = findViewById(R.id.vendor_shop_add_data)
       // val show_data: Button = findViewById(R.id.vendor_shop_show_data)
        vendor_Location_data = findViewById(R.id.vendor_Location_data)
        vendor_shop_chooseImage = findViewById(R.id.vendor_shop_choose_image)
        vendor_shop_name = findViewById(R.id.vendor_shop_name)
        vendor_shop_description = findViewById(R.id.vendor_shop_description)
        vendor_shop_location = findViewById(R.id.vendor_shop_location)
        vendor_shop_imageView = findViewById(R.id.vendor_shop_image)

        //Firebase Storage Initialization
        db = FirebaseStorage.getInstance()
        Storageref = db.reference


        mAuth = FirebaseAuth.getInstance()

//        show_data.setOnClickListener {
//
//            intent = Intent(this, Vendor_Shop_Show_Data::class.java)
//            startActivity(intent)
//
//        }



        vendor_Location_data.setOnClickListener{

            intent = Intent(this, Map_Vendor_shop::class.java)
            startActivity(intent)
        }



        vendor_shop_chooseImage.setOnClickListener {

            chooseImage()

        }



        add_data.setOnClickListener {

            var pd = ProgressDialog(this)
            pd.setTitle("SignUp in progress...")
            pd.setMessage("Please wait your data is adding")
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            pd.setProgress(0)
            pd.setMax(100)

            Add_Data()

        }
    }


    fun Add_Data() {

        //Real Time data base Initialization code
        root_Node = FirebaseDatabase.getInstance()
        reference = root_Node!!.getReference("Shop")

        var shop_name = vendor_shop_name.text.toString()
        var shop_description = vendor_shop_description.text.toString()
        var shop_location = vendor_shop_location.text.toString()


        // Only Image Uploading to cloud storage Code
        val ref: StorageReference = Storageref.child("Shop/" + UUID.randomUUID().toString())
        var uploadTask = ref.putFile(filepath)
        val urlTask =
            uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {

                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toasty.success(this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    val downloadUri = task.result

                    var shop_image = downloadUri.toString()
                    var model = Vendor_Shop_Model(shop_name,shop_image, shop_description, shop_location)
                    reference!!.child(shop_name).setValue(model).addOnCompleteListener {
                        Toasty.success(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT)
                            .show()


                        //Shared preferences get user name and retaurant/////////////////////////////////////////////////////////////////////
                        var  sharedPreferences = getSharedPreferences("shop_name", Context.MODE_PRIVATE)
                        var myEdit = sharedPreferences.edit()
                        myEdit.putString("shop_name",shop_name)
                        myEdit.putString("isRegistered","true")
                        myEdit.commit()

                        ///////////////////////////////////////////////////////////////////////

                        vendor_shop_name.setText("")
                        vendor_shop_description.setText("")
                        vendor_shop_location.setText("")

                        vendor_shop_name.isFocused
                        vendor_shop_name.isFocusable




                        vendor_shop_location.isEnabled = false
                        vendor_shop_location.isClickable = false
                        vendor_shop_name.isEnabled = false
                        vendor_shop_name.isClickable = false
                        vendor_shop_description.isClickable = false
                        vendor_shop_description.isEnabled = false
                        add_data.isClickable = false
                        add_data.isEnabled = false
                        vendor_shop_chooseImage.isEnabled = false
                        vendor_shop_chooseImage.isClickable = false
                        vendor_Location_data.isClickable = false
                        vendor_Location_data.isEnabled = false


                        sendVerification("false",shop_name)


                        Toasty.info(this, "Please wait for the confirmation of your restaurant data, a notification will be send to you after confirmation",
                            Toast.LENGTH_LONG).show()

                        var i = Intent(this, FSTRegisterPage::class.java)
                        startActivity(i)


                    }.addOnFailureListener {
                        Toasty.error(
                            this,
                            "Something went wrong, Check Connection",
                            Toast.LENGTH_LONG
                        ).show()
                    }


                } else {
                    // Handle failures
                    Toasty.info(this, "Something Went wrong, try again", Toast.LENGTH_SHORT)
                        .show()
                }
            }?.addOnFailureListener {

            }


    }


    fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST
            && resultCode == RESULT_OK
            && data != null
            && data.getData() != null
        ) {

            // Get the Uri of data
            filepath = data.getData()!!;
            try {

                // Setting image on image view using Bitmap
                var bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                vendor_shop_imageView.setImageBitmap(bitmap);
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace();
            }


        }
    }

    fun sendVerification(isVerified:String,name:String){



        reference = root_Node!!.getReference("Verification")

        var model = verification_model(name,isVerified)

        reference!!.child(name).setValue(model)



    }

    @SuppressLint("WrongConstant")
    fun isUserVerified(){






        var isVerified = ""

        var restaurantNamesharedP = getSharedPreferences("shop_name", Context.MODE_APPEND)

        var isRegistered = restaurantNamesharedP.getString("isRegistered","false").toString()
        var shop_name = restaurantNamesharedP.getString("shop_name","anonymous").toString()

        reference = FirebaseDatabase.getInstance().getReference()

        var query = reference!!.child("Verification").child(shop_name)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    isVerified = snapshot.child("verified").getValue().toString()
                    Toasty.success(this@Vendor_Shop_Add_Data, "value = " + isVerified, Toast.LENGTH_SHORT).show()


                }

                if(isVerified.equals("true")){

                    Toasty.success(this@Vendor_Shop_Add_Data, "You are Verified now you can Add Data " , Toast.LENGTH_LONG).show()


                    var intent = Intent(this@Vendor_Shop_Add_Data, Vendor_Shop_Category_Add_Data::class.java)
                    startActivity(intent)
                }

                else{
                    Toasty.error(this@Vendor_Shop_Add_Data, "verified = " + isVerified + "not verified yet" , Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Vendor_Shop_Add_Data, "something went wrong", Toast.LENGTH_SHORT).show()
            }

        })






    }
}



