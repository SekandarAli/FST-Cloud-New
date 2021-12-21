package com.example.fst_cloud_new.Vendor_Travel

import android.app.ProgressDialog
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
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SIGN_UP.FST_Vendor_Signup
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

class Vendor_Travel_Add_Data  : AppCompatActivity() {


    lateinit var next: Button


    var root_Node: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    lateinit var vendor_travel_imageView: ImageView
    lateinit var mAuth: FirebaseAuth
    lateinit var vendor_travel_name: EditText
    lateinit var vendor_travel_description: EditText
    lateinit var vendor_travel_location: EditText
    lateinit var vendor_travel_chooseImage: Button
    lateinit var vendor_travel_back: Button

    //Strings and constant

    var location: String = ""
    var PICK_IMAGE_REQUEST = 22

    //Database storage variables

    lateinit var db: FirebaseStorage
    lateinit var Storageref: StorageReference
    lateinit var filepath: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_travel_add_data)


        vendor_travel_back = findViewById(R.id.add_data_back)

        vendor_travel_back.setOnClickListener {

            intent = Intent(this, FST_Vendor_Signup::class.java)
            startActivity(intent)

        }


        val add_data: Button = findViewById(R.id.vendor_travel_add_data)
        val show_data: Button = findViewById(R.id.vendor_travel_show_data)
        val vendor_Location_data: Button = findViewById(R.id.vendor_Location_data)
        vendor_travel_chooseImage = findViewById(R.id.vendor_travel_choose_image)
        vendor_travel_name = findViewById(R.id.vendor_travel_name)
        vendor_travel_description = findViewById(R.id.vendor_travel_description)
        vendor_travel_location = findViewById(R.id.vendor_travel_location)
        vendor_travel_imageView = findViewById(R.id.vendor_travel_image)

        //Firebase Storage Initialization
        db = FirebaseStorage.getInstance()
        Storageref = db.reference


        mAuth = FirebaseAuth.getInstance()

        show_data.setOnClickListener {

            intent = Intent(this, Vendor_Travel_Show_Data::class.java)
            startActivity(intent)

        }



        vendor_Location_data.setOnClickListener{

            intent = Intent(this, Map_Vendor::class.java)
            startActivity(intent)
        }



        vendor_travel_chooseImage.setOnClickListener {

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
        reference = root_Node!!.getReference("Travel")

        var travel_name = vendor_travel_name.text.toString()
        var travel_description = vendor_travel_description.text.toString()
        var travel_location = vendor_travel_location.text.toString()


        // Only Image Uploading to cloud storage Code
        val ref: StorageReference = Storageref.child("Travel/" + UUID.randomUUID().toString())
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

                    var travel_image = downloadUri.toString()
                    var model = Vendor_Travel_Model(travel_name,travel_image, travel_description, travel_location)
                    reference!!.child(travel_name).setValue(model).addOnCompleteListener {
                        Toasty.success(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT)
                            .show()


                        vendor_travel_name.setText("")
                        vendor_travel_description.setText("")
                        vendor_travel_location.setText("")

                        vendor_travel_name.isFocused
                        vendor_travel_name.isFocusable

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
                vendor_travel_imageView.setImageBitmap(bitmap);
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace();
            }


        }
    }
}



