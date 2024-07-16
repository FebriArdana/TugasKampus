package com.example.project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ItemRiwayatActivity extends AppCompatActivity {
    private Spinner spKategori;
    private TextInputEditText etBerat, etHarga, etTanggal, etAlamat, etTambahan, etNama;
    private Button btnJemput;
    private String[] kategoriSampah = {"Baju", "Celana", "Jas", "Karpet", "Selimut", "Spring Bed"};
    private double hargaBaju, hargaCelana, hargaJas, hargaKarpet, hargaSelimut, hargaSpringBed;
    private Calendar calendar;
    private int year, month, day;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_riwayat);

        // Initialize Firebase
        reference = FirebaseDatabase.getInstance("https://project-af67c-default-rtdb.firebaseio.com/").getReference();

        // Initialize variables
        spKategori = findViewById(R.id.spKategori);
        etBerat = findViewById(R.id.berat);
        etHarga = findViewById(R.id.inputharga);
        etTanggal = findViewById(R.id.tanggal);
        etAlamat = findViewById(R.id.alamat);
        etTambahan = findViewById(R.id.Tambahan);
        etNama = findViewById(R.id.nama);
        btnJemput = findViewById(R.id.jemput_sampah_button);

        // Set adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kategoriSampah);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKategori.setAdapter(adapter);

        // Set default values for harga
        hargaBaju = 7000;
        hargaCelana = 7000;
        hargaSelimut = 10000;
        hargaJas = 10000;
        hargaKarpet = 15000;
        hargaSpringBed = 15000;

        // Set initial values for date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        etTanggal.setText(day + "/" + (month + 1) + "/" + year);

        // Handle spinner selection
        spKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kategori = kategoriSampah[position];
                double harga = 0;

                switch (kategori) {
                    case "Baju":
                        harga = hargaBaju;
                        break;
                    case "Celana":
                        harga = hargaCelana;
                        break;
                    case "Jas":
                        harga = hargaJas;
                        break;
                    case "Karpet":
                        harga = hargaKarpet;
                        break;
                    case "Selimut":
                        harga = hargaSelimut;
                        break;
                    case "Spring Bed":
                        harga = hargaSpringBed;
                        break;

                }

                etHarga.setText("Rp. " + harga + "/Kg");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Handle date selection
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ItemRiwayatActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etTanggal.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Handle button click
        btnJemput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemRiwayatActivity.this, RiwayatActivity.class);
                startActivity(intent);
                final String nama = etNama.getText().toString();
                final String kategori = spKategori.getSelectedItem().toString();
                final double berat = Double.parseDouble(etBerat.getText().toString());
                double harga = 0;

                switch (kategori) {
                    case "Baju":
                        harga = hargaBaju;
                        break;
                    case "Celana":
                        harga = hargaCelana;
                        break;
                    case "Jas":
                        harga = hargaJas;
                        break;
                    case "Karpet":
                        harga = hargaKarpet;
                        break;
                    case "Selimut":
                        harga = hargaSelimut;
                        break;
                    case "Spring Bed":
                        harga = hargaSpringBed;
                        break;
                }

                final double saldo = berat * harga;

                // Simpan data ke Firebase Realtime Database di bawah nama pengguna
                final DatabaseReference userRef = reference.child("hasil transaksi").child(nama);
                userRef.child("nama").setValue(nama);
                userRef.child("kategori").setValue(kategori);
                userRef.child("berat").setValue(berat);
                userRef.child("saldo").setValue(saldo); // Menggunakan setter untuk menyimpan saldo
                userRef.child("tanggal").setValue(etTanggal.getText().toString());
                userRef.child("alamat").setValue(etAlamat.getText().toString());
                userRef.child("tambahan").setValue(etTambahan.getText().toString());
                userRef.child("status").setValue("pending"); // Menambahkan status transaksi

                Toast.makeText(ItemRiwayatActivity.this, "Transaksi Berhasil", Toast.LENGTH_SHORT).show();

                // Implementasi notifikasi jika diperlukan
                // Misalnya menggunakan Firebase Cloud Messaging (FCM)
                // Anda dapat menambahkan logika notifikasi di sini
            }
        });
    }
}