package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TambahActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TransaksiAdapter adapter;
    private List<Transaksi> transaksiList;
    private DatabaseReference reference;
    private TextView notFound;
    private double saldoAkhir = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.sptambah);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.sphome){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;
                }
                if (id == R.id.splokasi){
                    startActivity(new Intent(getApplicationContext(), ArtikelActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;
                }
                if (id == R.id.sptambah){
                    return true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.historiRecyclerView);
        notFound = findViewById(R.id.notfpund);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transaksiList = new ArrayList<>();
        adapter = new TransaksiAdapter(transaksiList);
        recyclerView.setAdapter(adapter);

        // Inisialisasi Firebase
        reference = FirebaseDatabase.getInstance("https://project-af67c-default-rtdb.firebaseio.com/").getReference();

        // Mengambil data dari Firebase
        reference.child("hasil transaksi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                transaksiList.clear();
                saldoAkhir = 0.0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Transaksi transaksi = dataSnapshot.getValue(Transaksi.class);
                    if (transaksi != null) {
                        transaksiList.add(transaksi);
                        if ("Transaksi Berhasil".equals(transaksi.getStatus())) {
                            saldoAkhir += transaksi.getSaldo(); // Tambahkan saldo transaksi ke saldo akhir jika status berhasil
                        }
                    }
                }
                adapter.notifyDataSetChanged(); // Perbarui RecyclerView

                if (transaksiList.isEmpty()) {
                    notFound.setVisibility(View.VISIBLE);
                } else {
                    notFound.setVisibility(View.GONE);
                }

                // Tampilkan saldo akhir
                TextView saldoText = findViewById(R.id.saldo);
                saldoText.setText("Saldo Akhir: Rp. " + saldoAkhir);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Menangani kemungkinan kesalahan
            }
        });
    }
}