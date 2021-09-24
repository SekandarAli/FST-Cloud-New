package com.example.fst_cloud_new.Start_Pages

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fst_cloud_new.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty

class FSTForgetpassword : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fstforgetpassword)

        supportActionBar?.hide()

        auth = Firebase.auth

        val btn = findViewById<Button>(R.id.button)
        val forgetEditText = findViewById<EditText>(R.id.forgetEditText)

        btn.setOnClickListener(View.OnClickListener {

            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (forgetEditText.text.toString().isNullOrEmpty())
                Toasty.info(this,"Email Address is not provided", Toast.LENGTH_LONG).show()
            else {
                auth.sendPasswordResetEmail(
                    forgetEditText.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            Toasty.success(this, "Reset Password Link is mailed", Toast.LENGTH_LONG).show()
                        } else
                            Toasty.warning(this, "Password Reset mail could not be sent", Toast.LENGTH_LONG).show()
                    }
            }

        })

    }
}