package com.example.fst_cloud_new.Vendor_Dish

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
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.VENDOR.Vendor_Main_Page
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import es.dmoral.toasty.Toasty
import java.io.IOException
import java.util.*

class Vendor_Dish_Add_Data : AppCompatActivity() {


    var root_Node: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    lateinit var vendor_imageView: ImageView
    lateinit var mAuth: FirebaseAuth
    lateinit var vendor_dish_name: EditText
    lateinit var vendor_dish_description: EditText
    lateinit var vendor_dish_price: EditText
    lateinit var vendor_chooseImage: Button
    lateinit var vendor_dish_back: Button
    lateinit var vendor_search_data: Button

    //Strings and constant
    // var dish_image : String = ""
    var location: String = ""
    var PICK_IMAGE_REQUEST = 22

    //Database storage variables

    lateinit var db: FirebaseStorage
    lateinit var Storageref: StorageReference
    lateinit var filepath: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_dish_add_data)


        val add_data: Button = findViewById(R.id.vendor_add_data)
        val show_data: Button = findViewById(R.id.vendor_show_data)
        vendor_chooseImage = findViewById(R.id.vendor_choose_image)
        vendor_dish_name = findViewById(R.id.vendor_food_name)
        vendor_dish_description = findViewById(R.id.vendor_food_description)
        vendor_dish_price = findViewById(R.id.vendor_food_price)
        vendor_imageView = findViewById(R.id.vendor_image)
        vendor_dish_back = findViewById(R.id.vendor_dish_back)


        //Firebase Storage Initialization
        db = FirebaseStorage.getInstance()
        Storageref = db.reference


        mAuth = FirebaseAuth.getInstance()


        vendor_chooseImage.setOnClickListener {

            chooseImage()

        }

        vendor_dish_back.setOnClickListener {


            var intent = Intent(this, Vendor_Main_Page::class.java)
            startActivity(intent)

        }


        show_data.setOnClickListener {


            var intent = Intent(this, Vendor_Dish_Show_Data::class.java)
            startActivity(intent)

        }

        add_data.setOnClickListener {

            Add_Data()

        }

    }


    fun Add_Data() {

        //Real Time data base Initialization code
        root_Node = FirebaseDatabase.getInstance()
        reference = root_Node!!.getReference("Dish")

        var dish_name = vendor_dish_name.text.toString()
        var dish_description = vendor_dish_description.text.toString()
        var dish_price = vendor_dish_price.text.toString()

        if (vendor_dish_name.text.isNullOrEmpty()) {
            vendor_dish_name?.error ="Name cannot be empty."
            vendor_dish_name.isFocusable
        }

        if (vendor_dish_description.text.isNullOrEmpty()) {
            vendor_dish_description?.error ="Description cannot be empty."
            vendor_dish_description.isFocusable
        }

        if (vendor_dish_price.text.isNullOrEmpty()) {
            vendor_dish_price?.error ="Price cannot be empty."
            vendor_dish_price.isFocusable
        }



        else {

            // Only Image Uploading to cloud storage Code
            val ref: StorageReference = Storageref.child("Dish/" + UUID.randomUUID().toString())
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

                        Toasty.success(this, "Image Uploaded Successfully", Toast.LENGTH_SHORT)
                            .show()
                        val downloadUri = task.result

                        var dish_image = downloadUri.toString()


                        var model =
                            Vendor_Dish_Model(dish_name, dish_image, dish_description, dish_price)
                        reference!!.child(dish_name).setValue(model).addOnCompleteListener {
                            Toasty.success(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT)
                                .show()


                            vendor_dish_name.setText("")
                            vendor_dish_description.setText("")
                            vendor_dish_price.setText("")

                            vendor_dish_name.isFocused
                            vendor_dish_name.isFocusable

                        }.addOnFailureListener {
                            Toasty.error(
                                this,
                                "Something went wrong, Check Connection",
                                Toast.LENGTH_LONG
                            ).show()
                        }


                    } else {
                        // Handle failures
                        Toasty.warning(this, "Something Went wrong, try again", Toast.LENGTH_SHORT)
                            .show()
                    }
                }?.addOnFailureListener {

                }
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
                vendor_imageView.setImageBitmap(bitmap);
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace();
            }


        }
    }
}



