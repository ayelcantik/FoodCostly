package com.example.resepmakanan; // Ganti dengan nama package kamu

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import com.example.foodcostly.R;


public class LihatResepkuJava extends AppCompatActivity {

    ImageButton btnBack, btnEdit, btnTimer, btnHome, btnProfile;
    TextView txtJudulResep, txtBahanResep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatresep);

        btnBack = findViewById(R.id.button_back);
        btnEdit = findViewById(R.id.button_edit);
        btnTimer = findViewById(R.id.button_timer);
        btnHome = findViewById(R.id.button_home);
        btnProfile = findViewById(R.id.button_profile);
        txtJudulResep = findViewById(R.id.text_judul_resep);
        txtBahanResep = findViewById(R.id.text_bahan_resep);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}