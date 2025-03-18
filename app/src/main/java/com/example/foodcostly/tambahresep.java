package com.example.foodcostly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class tambahresep extends AppCompatActivity {

    ImageButton btnBack, btnTimer, btnHome, btnProfile, btnRefresh;
    EditText editNamaBahan, editJumlahBahan; // EditText untuk jumlah
    Spinner spinnerSatuan;
    Button btnTambahkan, btnSimpan;
    RecyclerView recyclerViewBahan;
    BahanAdapter bahanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresep);

        btnBack = findViewById(R.id.button_back);
        btnTimer = findViewById(R.id.button_timer);
        btnHome = findViewById(R.id.button_home);
        btnProfile = findViewById(R.id.button_profile);
        btnRefresh = findViewById(R.id.button_refresh);
        editNamaBahan = findViewById(R.id.edit_nama_bahan);
        editJumlahBahan = findViewById(R.id.edit_jumlah_bahan); // Inisialisasi EditText jumlah
        spinnerSatuan = findViewById(R.id.spinner_satuan);
        btnTambahkan = findViewById(R.id.button_tambahkan);
        btnSimpan = findViewById(R.id.button_simpan);
        recyclerViewBahan = findViewById(R.id.recycler_view_bahan);

        bahanAdapter = new BahanAdapter();
        recyclerViewBahan.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBahan.setAdapter(bahanAdapter);

        btnTambahkan.setOnClickListener(view -> tambahItemBahan());

        btnBack.setOnClickListener(v -> finish());

        btnSimpan.setOnClickListener(v -> {
            // TODO: Implementasi penyimpanan resep
            Toast.makeText(tambahresep.this, "Resep disimpan (belum diimplementasikan)", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Set listener untuk RecyclerView (edit dan delete)
        bahanAdapter.setOnItemClickListener(new BahanAdapter.OnItemClickListener() {

            @Override
            public void onEditClick(com.example.FoodCostly.Bahan bahan, int position) {

            }

            @Override
            public void onDeleteClick(com.example.FoodCostly.Bahan bahan, int position) {

            }

            @Override
            public void onDeleteClick(com.example.FoodCostly.Bahan bahan, int position) {
                bahanAdapter.getListBahan().remove(position);
                bahanAdapter.notifyItemRemoved(position);
            }
        });

        //Set data satuan ke spinner
        spinnerSatuan.setAdapter(new android.widget.ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.satuan_array)));
    }

    private void tambahItemBahan() {
        String namaBahan = editNamaBahan.getText().toString().trim();
        String jumlahBahan = editJumlahBahan.getText().toString().trim(); // Ambil jumlah
        String satuan = spinnerSatuan.getSelectedItem().toString();

        // Validasi
        if (namaBahan.isEmpty() || jumlahBahan.isEmpty()) {
            Toast.makeText(this, "Nama bahan dan jumlah harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Konversi jumlah ke double (untuk jaga-jaga jika ada desimal)
        try {
            Double.parseDouble(jumlahBahan);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Jumlah bahan harus berupa angka!", Toast.LENGTH_SHORT).show();
            return;
        }


        com.example.FoodCostly.Bahan bahanBaru = new com.example.FoodCostly.Bahan(namaBahan, jumlahBahan, satuan); // Gunakan jumlahBahan (String)
        (bahanBaru) = bahanAdapter.getListBahan().add(bahanBaru);
        bahanAdapter.notifyItemInserted(bahanAdapter.getListBahan().size() - 1);
        recyclerViewBahan.smoothScrollToPosition(bahanAdapter.getListBahan().size() - 1);

        editNamaBahan.setText("");
        editJumlahBahan.setText(""); // Kosongkan EditText jumlah
        spinnerSatuan.setSelection(0);
    }
}