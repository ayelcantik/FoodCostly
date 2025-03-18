package com.example.foodcostly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BahanAdapter extends RecyclerView.Adapter<BahanAdapter.BahanViewHolder> {

    private List<com.example.FoodCostly.Bahan> listBahan;
    private OnItemClickListener listener;

    public BahanAdapter() {
        this.listBahan = new ArrayList<>();
    }

    public void setListBahan(List<Bahan> listBahan) {
        this.listBahan = listBahan;
        notifyDataSetChanged();
    }

    public List<Bahan> getListBahan() {
        return this.listBahan;
    }

    public interface OnItemClickListener {
        void onEditClick(Bahan bahan, int position);
        void onDeleteClick(Bahan bahan, int position);

        void onDeleteClick(com.example.FoodCostly.Bahan bahan, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BahanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bahan, parent, false);
        return new BahanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BahanViewHolder holder, int position) {
        Bahan bahan = listBahan.get(position);
        holder.textNamaBahan.setText(bahan.getNama());
        holder.textJumlahSatuan.setText(bahan.getJumlah() + " " + bahan.getSatuan());

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(bahan, position);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(bahan, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBahan.size();
    }

    static class BahanViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaBahan, textJumlahSatuan;
        ImageButton btnEdit, btnDelete;

        public BahanViewHolder(@NonNull View itemView) {
            super(itemView);
            textNamaBahan = itemView.findViewById(R.id.text_item_nama_bahan);
            textJumlahSatuan = itemView.findViewById(R.id.text_item_jumlah_satuan);
            btnEdit = itemView.findViewById(R.id.button_item_edit);
            btnDelete = itemView.findViewById(R.id.button_item_delete);
        }
    }
}