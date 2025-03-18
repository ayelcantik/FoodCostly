package com.example.FoodCostly;

public class Bahan {
    private String nama;
    private String jumlah;
    private String satuan;

    public Bahan(String nama, String jumlah, String satuan) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.satuan = satuan;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJumlah() {
        return this.jumlah;
    }

    public String getSatuan() {
        return this.satuan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}