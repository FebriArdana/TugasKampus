package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdminTransaksiActivity extends RecyclerView.Adapter<ItemAdminTransaksiActivity.ViewHolder> {
    private List<Transaksi> transaksiList;
    private OnConfirmClickListener confirmClickListener;

    public interface OnConfirmClickListener {
        void onConfirmClick(Transaksi transaksi);
    }

    public ItemAdminTransaksiActivity(List<Transaksi> transaksiList, OnConfirmClickListener confirmClickListener) {
        this.transaksiList = transaksiList;
        this.confirmClickListener = confirmClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_admin_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaksi transaksi = transaksiList.get(position);
        holder.nama.setText(transaksi.getNama());
        holder.kategori.setText(transaksi.getKategori());
        holder.berat.setText(String.format("Berat: %.2f Kg", transaksi.getBerat()));
        holder.saldo.setText(String.format("Saldo: Rp. %.2f", transaksi.getSaldo()));
        holder.status.setText(transaksi.getStatus());
        holder.alamat.setText(transaksi.getAlamat());

        holder.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmClickListener != null) {
                    confirmClickListener.onConfirmClick(transaksi); // Mengirimkan transaksi yang dikonfirmasi
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama, kategori, berat, saldo, status,alamat;
        public Button confirmButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.Nama);
            kategori = itemView.findViewById(R.id.kategori);
            berat = itemView.findViewById(R.id.Berat);
            saldo = itemView.findViewById(R.id.Saldo);
            status = itemView.findViewById(R.id.Status);
            confirmButton = itemView.findViewById(R.id.confirmButton);
            alamat = itemView.findViewById(R.id.Alamat);
        }
    }
}