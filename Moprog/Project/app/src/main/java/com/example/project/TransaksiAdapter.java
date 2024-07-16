package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {
    private List<Transaksi> transaksiList;

    public TransaksiAdapter(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_riwayatin, parent, false);
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
        holder.alamat.setText(transaksi.getAlamat()); // Bind alamat
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama, kategori, berat, saldo, status, alamat;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.Nama);
            kategori = itemView.findViewById(R.id.kategori);
            berat = itemView.findViewById(R.id.Berat);
            saldo = itemView.findViewById(R.id.Saldo);
            status = itemView.findViewById(R.id.Status);
            alamat = itemView.findViewById(R.id.Alamat); // Initialize alamat
        }
    }
}
