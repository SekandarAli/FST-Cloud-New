package com.example.fst_cloud_new.Start_Pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import es.dmoral.toasty.Toasty
import java.util.concurrent.TimeUnit

class OTP : AppCompatActivity() {


    private lateinit var callBacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks

    lateinit var storedVerificationId : String
    lateinit var resendToken : PhoneAuthProvider.ForceResendingToken

    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)


        auth = FirebaseAuth.getInstance()

        val sendotp : Button = findViewById(R.id.send_otp_btn)
        val USEROTP : EditText = findViewById(R.id.USEROTP)
        val verifyotp : Button = findViewById(R.id.verify_otp_btn)
        val otp1 : EditText = findViewById(R.id.otp_edit_box1)
        val otp2 : EditText = findViewById(R.id.otp_edit_box2)
        val otp3 : EditText = findViewById(R.id.otp_edit_box3)
        val otp4 : EditText = findViewById(R.id.otp_edit_box4)
        val otp5 : EditText = findViewById(R.id.otp_edit_box5)
        val otp6 : EditText = findViewById(R.id.otp_edit_box6)




        otp1.setOnKeyListener { v, keyCode, event ->


            if(otp1.text.length == 1)
            {
                otp2.requestFocus()
            }

            return@setOnKeyListener false
        }

             otp2.setOnKeyListener { v, keyCode, event ->


            if(otp2.text.length == 1)
            {
                otp3.requestFocus()
            }

            return@setOnKeyListener false
        }

             otp3.setOnKeyListener { v, keyCode, event ->


            if(otp3.text.length == 1)
            {
                otp4.requestFocus()
            }

            return@setOnKeyListener false
        }

             otp4.setOnKeyListener { v, keyCode, event ->


            if(otp4.text.length == 1)
            {
                otp5.requestFocus()
            }

            return@setOnKeyListener false
        }

             otp5.setOnKeyListener { v, keyCode, event ->


            if(otp5.text.length == 1)
            {
                otp6.requestFocus()
            }

            return@setOnKeyListener false
        }




        sendotp.setOnClickListener {

            val Phone = USEROTP.text.toString().trim()

            authentication("+92$Phone")


        }

        verifyotp.setOnClickListener {

            val otpp = otp1.text.toString()+otp2.text.toString()+otp3.text.toString()+
                    otp4.text.toString()+otp5.text.toString()+otp6.text.toString()

         otpp.trim()



            Toasty.info(this, "Your Entered Code = "+ otpp, Toast.LENGTH_SHORT).show()
            verifyVerification(otpp)


        }

        callBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {


                val code = credential.smsCode


            }

            override fun onVerificationFailed(e: FirebaseException) {


                Toasty.error(this@OTP, "Failed", Toast.LENGTH_SHORT).show()

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token


            }
        }



    }


    fun authentication(PhoneNo : String)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(

            PhoneNo,
            60,
            TimeUnit.SECONDS,
            this,
            callBacks
        )
    }

    fun verifyVerification(code : String)
    {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId,code)
        signup(credential)


    }

    private fun signup(credential: PhoneAuthCredential)
    {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user
                    Toasty.success(this, "Code Successfull", Toast.LENGTH_SHORT).show()

                    intent = Intent(this, HOMEPAGE::class.java)
                    startActivity(intent)

                }
                else
                {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                        Toasty.info(this, "Code Entered is incorrect", Toast.LENGTH_SHORT).show()

                        intent = Intent(this, FSTRegisterPage::class.java)
                        startActivity(intent)
                    }

                }
            }

    }
}

