package com.example.fst_cloud_new.Profile_Data

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.R
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

class Profile_Dummy : AppCompatActivity() {

    lateinit var next: Button


    var root_Node: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    lateinit var profile_image: ImageView
    lateinit var mAuth: FirebaseAuth
    lateinit var profile_name: EditText
    lateinit var profile_email: EditText
    lateinit var profile_image_button: Button
    lateinit var profile_add_data: Button

    //Strings and constant

    var location: String = ""
    var PICK_IMAGE_REQUEST = 22

    //Database storage variables

    lateinit var db: FirebaseStorage
    lateinit var Storageref: StorageReference
    lateinit var filepath: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_dummy)

        supportActionBar?.hide()

        profile_image = findViewById(R.id.profile_image)
        profile_name = findViewById(R.id.profile_name)
        profile_email = findViewById(R.id.profile_email)
        profile_image_button = findViewById(R.id.profile_image_button)
        profile_add_data = findViewById(R.id.profile_add_data)

        //Firebase Storage Initialization
        db = FirebaseStorage.getInstance()
        Storageref = db.reference


        mAuth = FirebaseAuth.getInstance()




        profile_image_button.setOnClickListener {

            chooseImage()

        }



        profile_add_data.setOnClickListener {

            var pd = ProgressDialog(this)
            pd.setTitle("SignUp in progress...")
            pd.setMessage("Please wait your data is adding")

            Add_Data()
//
//            var intent = Intent(this,HOMEPAGE::class.java)
//
//            intent.putExtra(HOMEPAGE.NAME,profile_name)
//
//
//            //intent.putExtra("image",items[position].image)
//
//            startActivity(intent)

        }
    }


    fun Add_Data() {

        //Real Time data base Initialization code
        root_Node = FirebaseDatabase.getInstance()
        reference = root_Node!!.getReference("Profile")

        var profile_name = profile_name.text.toString()
        var profile_email = profile_email.text.toString()


        // Only Image Uploading to cloud storage Code
        val ref: StorageReference = Storageref.child("Profile/" + UUID.randomUUID().toString())
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

                    var profile_image = downloadUri.toString()
                    var model = Profile_Model(profile_name,profile_image,profile_email)
                    reference!!.child(profile_name).setValue(model).addOnCompleteListener {
                        Toasty.success(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT)
                            .show()

                        intent = Intent(this,HOMEPAGE::class.java)
                        startActivity(intent)


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
                profile_image.setImageBitmap(bitmap);
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace();
            }


        }
    }
}



