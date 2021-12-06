package com.example.fst_cloud_new.PROFILES;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

import com.example.fst_cloud_new.R;

public class About extends AppCompatActivity {

            ConstraintLayout expandableView;
            Button arrowBtn;
            CardView cardView;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_about);


                expandableView = findViewById(R.id.expandableView);
                arrowBtn = findViewById(R.id.arrowBtn);
                cardView = findViewById(R.id.cardView);

                arrowBtn.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {
                        if (expandableView.getVisibility()==View.GONE){
                            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                            expandableView.setVisibility(View.VISIBLE);
                            arrowBtn.setBackgroundResource(R.drawable.ic_arrow_upward);
                        } else {
                            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                            expandableView.setVisibility(View.GONE);
                            arrowBtn.setBackgroundResource(R.drawable.ic_arrow_downward);
                        }
                    }
                });
            }
        }
