package com.example.foodcostly;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListResep extends AppCompatActivity {

    ImageButton btnBack, btnTimer, btnHome, btnProfile;
    FloatingActionButton fabAdd;
    TextView txtNamaResep, txtTanggalResep, txtDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listresep);

        btnBack = findViewById(R.id.button_back);
        btnTimer = findViewById(R.id.button_timer);
        btnHome = findViewById(R.id.button_home);
        btnProfile = findViewById(R.id.button_profile);
        fabAdd = findViewById(R.id.fab_add);
        txtNamaResep = findViewById(R.id.text_nama_resep);
        txtTanggalResep = findViewById(R.id.text_tanggal_resep);
        txtDeskripsi = findViewById(R.id.text_deskripsi_singkat);

        // Contoh data dummy
        txtNamaResep.setText("Cookies");
        txtTanggalResep.setText("20/02/2025");
        txtDeskripsi.setText("200 gram mentega, 150 gram gula halus...");

        btnBack.setOnClickListener(v -> finish());

        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(ListResep.this, TambahResep.class);
            startActivity(intent);
        });
    }
}