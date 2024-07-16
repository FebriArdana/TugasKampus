package com.example.project;

public class Transaksi {
    private String id;
    private String nama;
    private String kategori;
    private double berat;
    private double saldo;
    private String status;

    private String tambahan;

    private String Tanggal;
    private String alamat;  // Tambahkan atribut alamat

    public Transaksi() {
        // Default constructor required for calls to DataSnapshot.getValue(Transaksi.class)
    }

    public Transaksi(String id, String nama, String kategori, double berat, double saldo, String status,String Tanggal,String tambahan, String alamat) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.berat = berat;
        this.saldo = saldo;
        this.status = status;
        this.tambahan = tambahan;
        this.Tanggal = Tanggal;
        this.alamat = alamat;  // Tambahkan atribut alamat
    }

    // Getter dan setter untuk alamat
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    // Getter dan setter lainnya...
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTambahan(){return tambahan;}
    public void setTambahan(String tambahan){this.tambahan=tambahan;}

    public String getTanggal(){return Tanggal; }

    public void setTanggal(String Tanggal){ this.Tanggal = Tanggal;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}