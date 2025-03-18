package com.example.resepmakanan; // Ganti dengan nama package kamu

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat; // Import ini

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodcostly.R;

public class TambahResepJava extends AppCompatActivity {

    ImageButton btnBack, btnTimer, btnHome, btnProfile, btnRefresh;
    EditText editNamaBahan, editJumlahBahan;
    Spinner spinnerSatuan;
    Button btnTambahkan, btnSimpan;
    LinearLayout layoutListBahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresep);

        btnBack = findViewById(R.id.button_back);
        btnTimer = findViewById(R.id.button_timer);
        btnHome = findViewById(R.id.button_home);
        btnProfile = findViewById(R.id.button_profile);
        btnRefresh = findViewById(R.id.button_refresh); // Tambahkan ini
        editNamaBahan = findViewById(R.id.edit_nama_bahan);
        editJumlahBahan = findViewById(R.id.edit_jumlah_bahan);
        spinnerSatuan = findViewById(R.id.spinner_satuan);
        btnTambahkan = findViewById(R.id.button_tambahkan);
        btnSimpan = findViewById(R.id.button_simpan);
        layoutListBahan = findViewById(R.id.layout_list_bahan);

        btnTambahkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahItemBahan();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() { // Tambahkan ini
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() { // Tambahkan aksi untuk simpan
            @Override
            public void onClick(View v) {
                // TODO: Implementasi logika penyimpanan resep (ke database/shared preferences)
                Toast.makeText(TambahResepJava.this, "Resep disimpan (belum diimplementasikan)", Toast.LENGTH_SHORT).show();
                finish(); // Kembali ke activity sebelumnya
            }
        });
    }

    private void tambahItemBahan() {
        String namaBahan = editNamaBahan.getText().toString().trim();
        String jumlahBahan = editJumlahBahan.getText().toString().trim();
        String satuan = spinnerSatuan.getSelectedItem().toString();

        // Validasi input
        if (namaBahan.isEmpty() || jumlahBahan.isEmpty()) {
            Toast.makeText(this, "Nama bahan dan jumlah harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Buat layout item bahan
        LinearLayout itemBahanLayout = new LinearLayout(this);
        itemBahanLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemBahanLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        itemBahanLayout.setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen.activity_vertical_margin)); // Tambahkan margin


        // Buat TextView untuk nama bahan
        TextView textNamaBahan = new TextView(this);
        textNamaBahan.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f  // Weight 1 agar mengambil space yang tersedia
        ));
        textNamaBahan.setText(namaBahan);
        textNamaBahan.setTextColor(ContextCompat.getColor(this, R.color.text_color)); // Ganti R.color.text_color dengan warna yang sesuai

        // Buat TextView untuk jumlah dan satuan
        TextView textJumlahSatuan = new TextView(this);
        textJumlahSatuan.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textJumlahSatuan.setText(jumlahBahan + " " + satuan);
        textJumlahSatuan.setTextColor(ContextCompat.getColor(this, R.color.text_color)); // Ganti R.color.text_color

        // Buat ImageButton untuk edit dan delete (opsional)
        ImageButton btnEdit = new ImageButton(this);
        btnEdit.setImageResource(R.drawable.ic_baseline_edit_24);
        btnEdit.setBackground(null); // Hilangkan background bawaan
        btnEdit.setContentDescription("Edit");

        ImageButton btnDelete = new ImageButton(this);
        btnDelete.setImageResource(R.drawable.ic_baseline_delete_24);
        btnDelete.setBackground(null); // Hilangkan background bawaan
        btnDelete.setContentDescription("Delete");

        // Tambahkan view ke dalam item layout
        itemBahanLayout.addView(textNamaBahan);
        itemBahanLayout.addView(textJumlahSatuan);
        itemBahanLayout.addView(btnEdit);
        itemBahanLayout.addView(btnDelete);

        // Tambahkan item layout ke dalam layout list bahan
        layoutListBahan.addView(itemBahanLayout);

        // Kosongkan input fields
        editNamaBahan.setText("");
        editJumlahBahan.setText("");
        spinnerSatuan.setSelection(0); // Reset spinner ke item pertama


        // Tambahkan listener untuk tombol delete (di dalam tambahItemBahan)
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutListBahan.removeView(itemBahanLayout); // Hapus item dari list
            }
        });

        // Tambahkan listener untuk tombol edit (di dalam tambahItemBahan)
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data dari TextView
                String nama = textNamaBahan.getText().toString();
                String jumlahSatuan = textJumlahSatuan.getText().toString();

                // Pisahkan jumlah dan satuan (asumsi formatnya "jumlah satuan")
                String[] parts = jumlahSatuan.split(" ");
                String jumlah = parts[0];
                String satuan = parts.length > 1 ? parts[1] : "";


                // Set data ke EditText dan Spinner
                editNamaBahan.setText(nama);
                editJumlahBahan.setText(jumlah);

                // Cari posisi satuan di spinner
                int spinnerPosition = -1;
                for (int i = 0; i < spinnerSatuan.getCount(); i++) {
                    if (spinnerSatuan.getItemAtPosition(i).toString().equalsIgnoreCase(satuan)) {
                        spinnerPosition = i;
                        break;
                    }
                }
                if(spinnerPosition != -1){
                    spinnerSatuan.setSelection(spinnerPosition);
                }

                // Hapus item lama dari tampilan, Nnti akan ditambahkan lagi setelah di edit
                layoutListBahan.removeView(itemBahanLayout);


            }
        });

    }
}