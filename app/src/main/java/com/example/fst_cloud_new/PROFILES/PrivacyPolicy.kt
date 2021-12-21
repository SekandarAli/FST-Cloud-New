package com.example.fst_cloud_new.PROFILES

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fst_cloud_new.R


class PrivacyPolicy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)


        val pp: Button = findViewById(R.id.pp)

        pp.setOnClickListener(View.OnClickListener {

            val url = "https://fstcloud.blogspot.com/2021/12/fst-cloud-privacy-policy.html"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        })
    }
}